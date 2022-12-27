package com.example.ticky.di

import com.example.ticky.core.Constants
import com.example.ticky.feature_task_groups.data.repository.GroupRepositoryImpl
import com.example.ticky.feature_task_groups.domain.repository.GroupRepository
import com.example.ticky.feature_task_groups.domain.use_cases.AddGroup
import com.example.ticky.feature_task_groups.domain.use_cases.DeleteGroup
import com.example.ticky.feature_task_groups.domain.use_cases.GetGroups
import com.example.ticky.feature_task_groups.domain.use_cases.GroupUseCases
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //  Firestore instance
    @Provides
    @Singleton
    fun provideFirebaseFirestore() = Firebase.firestore

    //  Groups collection instance
    @Provides
    @Singleton
    fun provideGroupsReference(
        db: FirebaseFirestore
    ) = db.collection(Constants.GROUP_COLLECTION)

    //  inject the collection reference to the repository implementation
    @Provides
    @Singleton
    fun provideGroupRepository(
        groupsRef: CollectionReference
    ) : GroupRepository = GroupRepositoryImpl(groupsRef)

    //  our use cases
    @Provides
    @Singleton
    fun provideGroupUseCases(
        repository: GroupRepository
    ) = GroupUseCases(
        addGroup = AddGroup(repository),
        deleteGroup = DeleteGroup(repository),
        getGroups = GetGroups(repository)
    )

}






































