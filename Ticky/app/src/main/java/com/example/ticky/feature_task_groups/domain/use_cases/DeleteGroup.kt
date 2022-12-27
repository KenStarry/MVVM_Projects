package com.example.ticky.feature_task_groups.domain.use_cases

import com.example.ticky.feature_task_groups.domain.model.Group
import com.example.ticky.feature_task_groups.domain.repository.GroupRepository

class DeleteGroup(
    private val repo: GroupRepository
) {

    suspend operator fun invoke(
        group: Group
    ) = repo.deleteGroupFromFirestore(group)
}