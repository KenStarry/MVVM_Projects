package com.example.mvvmformvalidation.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmformvalidation.domain.model.ValidationResult
import com.example.mvvmformvalidation.domain.use_case.ValidateUseCases
import com.example.mvvmformvalidation.presentation.model.RegistrationFormEvent
import com.example.mvvmformvalidation.presentation.model.RegistrationFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCases: ValidateUseCases
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    //  channel to communicate with our ui
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {

        when (event) {

            is RegistrationFormEvent.EmailChanged -> {
                //  change email state
                state = state.copy(email = event.email)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAccepted)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {

        val emailResult: ValidationResult = useCases.validateEmail.execute(state.email)
        val passwordResult: ValidationResult = useCases.validatePassword.execute(state.password)
        val repeatedPasswordResult: ValidationResult =
            useCases.validateRepeatedPassword.execute(
                state.password, state.repeatedPassword
            )
        val termsResult: ValidationResult = useCases.validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            termsResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }

    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}














