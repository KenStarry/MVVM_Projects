package com.example.mvvmformvalidation.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mvvmformvalidation.presentation.model.RegistrationFormState

class MainViewModel : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

}