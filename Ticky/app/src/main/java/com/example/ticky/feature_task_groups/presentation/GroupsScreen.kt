package com.example.ticky.feature_task_groups.presentation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ticky.feature_task_groups.presentation.components.GroupsBottomSheet
import com.example.ticky.feature_task_groups.presentation.components.GroupsFab
import com.example.ticky.feature_task_groups.presentation.components.GroupsTopBar
import com.example.ticky.feature_task_groups.presentation.components.bottom_sheet_content.AddGroupBottomSheet
import com.example.ticky.feature_task_groups.presentation.viewmodel.GroupsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun GroupsScreen(
    viewModel: GroupsViewModel = hiltViewModel()
) {

    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current

    GroupsBottomSheet(
        sheetBackgroundColor = Color.Transparent,
        sheetContent = { state, scope ->
            AddGroupBottomSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.Transparent)
                    .padding(16.dp),
                focusRequester = focusRequester,
                onInputChange = {},
                onSubmit = {value, colorInt ->

                    //  hide bottom sheet
                    viewModel.closeBottomSheet(state, scope)

                    //  remove focus
                    focusManager.clearFocus()
                },
                clearFocus = {
                    focusManager.clearFocus()
                }
            )
        },
        bottomSheetScope = { state, scope ->

            if (state.currentValue == ModalBottomSheetValue.Hidden) {
                focusManager.clearFocus()
            }

            //  Main Screen
            Scaffold(

                topBar = { GroupsTopBar() },
                floatingActionButton = {
                    GroupsFab(
                        onFabClicked = {
                            //  open bottomSheetDrawer
                            viewModel.openBottomSheet(state, scope)
                        }
                    )
                }

            ) { contentPadding ->

                //  Main Screen
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(contentPadding)
                ) {


                }

            }

        },
        closeBottomSheet = { state, scope ->
            viewModel.closeBottomSheet(state, scope)
        }
    )
}



























