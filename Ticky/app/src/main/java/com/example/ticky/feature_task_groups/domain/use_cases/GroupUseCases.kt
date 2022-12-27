package com.example.ticky.feature_task_groups.domain.use_cases

data class GroupUseCases(
    val addGroup: AddGroup,
    val deleteGroup: DeleteGroup,
    val getGroups: GetGroups
)
