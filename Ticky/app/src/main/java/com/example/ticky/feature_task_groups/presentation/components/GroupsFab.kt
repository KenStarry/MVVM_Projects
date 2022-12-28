package com.example.ticky.feature_task_groups.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable

@Composable
fun GroupsFab(
    onFabClicked: () -> Unit
) {
    FloatingActionButton(onClick = onFabClicked) {
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = "Add Icon"
        )
    }
}