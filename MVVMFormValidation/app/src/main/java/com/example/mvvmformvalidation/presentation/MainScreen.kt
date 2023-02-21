package com.example.mvvmformvalidation.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.mvvmformvalidation.domain.use_case.ValidateUseCases
import com.example.mvvmformvalidation.presentation.model.RegistrationFormEvent
import com.example.mvvmformvalidation.presentation.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val viewmodel = MainViewModel(ValidateUseCases())
    val state = viewmodel.state
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewmodel.validationEvents.collect { event ->
            when (event) {
                is MainViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //  email
        TextField(
            value = state.email,
            onValueChange = {
                viewmodel.onEvent(RegistrationFormEvent.EmailChanged(it))
            },
            isError = state.emailError != null,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        //  password
        TextField(
            value = state.password,
            onValueChange = {
                viewmodel.onEvent(RegistrationFormEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //  repeated password
        TextField(
            value = state.repeatedPassword,
            onValueChange = {
                viewmodel.onEvent(RegistrationFormEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}




















