package com.example.ticky.feature_task_groups.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.ticky.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsTopBar() {

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontWeight = FontWeight.ExtraBold
            )
        },
        actions = {
            //  More icon
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Sharp.MoreVert,
                    contentDescription = "More Icon"
                )
            }
        }
    )
}































