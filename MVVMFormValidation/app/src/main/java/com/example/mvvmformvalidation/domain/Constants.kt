package com.example.mvvmformvalidation.domain

object Constants {

    //  email validation
    const val BLANK_EMAIL_ERROR = "Email can't be blank"
    const val INVALID_EMAIL_ERROR = "That's not a valid email"

    //  password validation
    const val PASSWORD_LENGTH = 8
    const val PASSWORD_LENGTH_ERROR =
        "The password needs to contain at least $PASSWORD_LENGTH characters"
    const val PASSWORD_LETTERS_ERROR =
        "The password needs to contain at least one letter and digit"
}