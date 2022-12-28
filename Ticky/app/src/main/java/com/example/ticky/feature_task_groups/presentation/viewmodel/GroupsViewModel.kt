package com.example.ticky.feature_task_groups.presentation.viewmodel

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ticky.feature_task_groups.domain.model.Group
import com.example.ticky.feature_task_groups.domain.model.Response
import com.example.ticky.feature_task_groups.domain.repository.AddGroupResponse
import com.example.ticky.feature_task_groups.domain.repository.DeleteGroupResponse
import com.example.ticky.feature_task_groups.domain.repository.GroupsResponse
import com.example.ticky.feature_task_groups.domain.use_cases.GroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val useCases: GroupUseCases
) : ViewModel() {


    //  all groups state
    var groupsResponse by mutableStateOf<GroupsResponse>(Response.Loading)
        private set

    //  add groups state
    var addGroupResponse by mutableStateOf<AddGroupResponse>(Response.Success(false))
        private set

    //  delete groups state
    var deleteGroupResponse by mutableStateOf<DeleteGroupResponse>(Response.Success(false))
        private set

    init {
        getGroups()
    }

    //  Get all groups
    private fun getGroups() {

        viewModelScope.launch {
            useCases.getGroups().collect { data ->
                groupsResponse = data
            }
        }
    }

    //  add a group
    fun addGroup(group: Group) {
        viewModelScope.launch {

            addGroupResponse = Response.Loading
            addGroupResponse = useCases.addGroup(group)

            //  toggle the ui
            when (addGroupResponse) {
                is Response.Success -> {}

                is Response.Loading -> {}

                is Response.Failure -> {}
            }
        }
    }

    //  delete group
    fun deleteGroup(group: Group) {
        viewModelScope.launch {
            deleteGroupResponse = useCases.deleteGroup(group)
        }
    }

    //  open BottomSheet
    @OptIn(ExperimentalMaterialApi::class)
    fun openBottomSheet(state: ModalBottomSheetState, scope: CoroutineScope) {

        scope.launch {
            state.animateTo(
                ModalBottomSheetValue.Expanded, spring(
                    dampingRatio = 0.5f,
                    stiffness = Spring.StiffnessHigh
                )
            )
        }
    }

    //  close BottomSheet
    @OptIn(ExperimentalMaterialApi::class)
    fun closeBottomSheet(state: ModalBottomSheetState, scope: CoroutineScope) {

        scope.launch {
            state.animateTo(
                ModalBottomSheetValue.Hidden, spring(
                    dampingRatio = 0.5f,
                    stiffness = Spring.StiffnessHigh
                )
            )
        }
    }
}





































