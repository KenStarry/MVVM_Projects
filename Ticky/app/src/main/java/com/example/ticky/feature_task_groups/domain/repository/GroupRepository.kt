package com.example.ticky.feature_task_groups.domain.repository

import com.example.ticky.feature_task_groups.domain.model.Group
import com.example.ticky.feature_task_groups.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Groups = List<Group>
typealias GroupsResponse = Response<Groups>
typealias AddGroupResponse = Response<Boolean>
typealias DeleteGroupResponse = Response<Boolean>

interface GroupRepository {

    //  get all groups
    fun getGroupsFromFirestore(): Flow<GroupsResponse>

    //  add a group
    suspend fun addGroupToFirestore(
        group: Group
    ): AddGroupResponse

    //  delete a group
    suspend fun deleteGroupFromFirestore(
        group: Group
    ): DeleteGroupResponse

}