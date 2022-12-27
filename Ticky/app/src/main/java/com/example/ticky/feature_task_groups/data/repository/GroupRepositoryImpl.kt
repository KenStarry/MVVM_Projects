package com.example.ticky.feature_task_groups.data.repository

import com.example.ticky.feature_task_groups.domain.model.Group
import com.example.ticky.feature_task_groups.domain.model.Response
import com.example.ticky.feature_task_groups.domain.repository.AddGroupResponse
import com.example.ticky.feature_task_groups.domain.repository.DeleteGroupResponse
import com.example.ticky.feature_task_groups.domain.repository.GroupRepository
import com.example.ticky.feature_task_groups.domain.repository.GroupsResponse
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupsRef: CollectionReference
) : GroupRepository {

    //  getting all groups
    override fun getGroupsFromFirestore(): Flow<GroupsResponse> = callbackFlow {

        val snapshotListener = groupsRef.addSnapshotListener { snapshot, error ->

            val snapshotResponse = if (snapshot != null) {

                val groups = snapshot.toObjects(Group::class.java)
                Response.Success(groups)
            } else {
                Response.Failure(error)
            }

            trySend(snapshotResponse)
        }

        //  await for the listener
        awaitClose { snapshotListener.remove() }
    }

    //  add group to firestore
    override suspend fun addGroupToFirestore(group: Group): AddGroupResponse {

        return try {
            //  add the data as a document
            groupsRef.document(group.groupName!!).set(group).await()
            //  return success confirmation
            Response.Success(true)

        } catch (e: Exception) {
            //  return failure
            Response.Failure(e)
        }

    }

    override suspend fun deleteGroupFromFirestore(group: Group): DeleteGroupResponse {
        return try {

            //  delete the document
            groupsRef.document(group.groupName!!).delete().await()
            Response.Success(true)

        } catch (e: Exception) {

            Response.Failure(e)
        }
    }
}


























