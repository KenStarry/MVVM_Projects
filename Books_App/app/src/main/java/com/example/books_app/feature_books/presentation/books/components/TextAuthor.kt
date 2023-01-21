package com.example.books_app.feature_books.presentation.books.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun TextAuthor(
    bookAuthor: String
) {

    Text(
        text = "by $bookAuthor",
        color = Color.DarkGray,
        fontSize = 25.sp,
        textDecoration = TextDecoration.Underline
    )
}