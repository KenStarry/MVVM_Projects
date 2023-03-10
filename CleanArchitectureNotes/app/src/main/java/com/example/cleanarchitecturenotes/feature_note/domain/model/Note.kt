package com.example.cleanarchitecturenotes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitecturenotes.ui.theme.*

//  our note model class
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, BabyBlue, Violet, RedPink)
    }
}

//  Exception handling
class InvalidNoteException(message: String) : Exception(message)
