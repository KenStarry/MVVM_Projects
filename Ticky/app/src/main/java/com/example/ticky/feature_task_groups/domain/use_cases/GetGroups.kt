package com.example.ticky.feature_task_groups.domain.use_cases

import com.example.ticky.feature_task_groups.domain.repository.GroupRepository
import com.example.ticky.feature_task_groups.domain.repository.GroupsResponse
import kotlinx.coroutines.flow.Flow

class GetGroups(
    private val repo: GroupRepository
) {

    operator fun invoke(): Flow<GroupsResponse> = repo.getGroupsFromFirestore()
}