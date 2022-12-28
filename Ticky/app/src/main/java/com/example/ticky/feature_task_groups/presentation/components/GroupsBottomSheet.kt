package com.example.ticky.feature_task_groups.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupsBottomSheet(
    sheetContent: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    sheetBackgroundColor: Color,
    bottomSheetScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    closeBottomSheet: (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {

    //  state of our bottomsheet
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            sheetContent(modalBottomSheetState, scope)
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = sheetBackgroundColor,
        sheetElevation = 0.dp
    ) {

        bottomSheetScope(modalBottomSheetState, scope)

    }

    BackHandler(onBack = {
        //  close bottomsheet
        closeBottomSheet(modalBottomSheetState, scope)
    })

}






















