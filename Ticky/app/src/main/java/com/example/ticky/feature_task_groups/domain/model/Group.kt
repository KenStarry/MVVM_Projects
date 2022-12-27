package com.example.ticky.feature_task_groups.domain.model

import com.example.ticky.feature_tasks.domain.model.Task
import com.example.ticky.ui.theme.*

data class Group(
    val groupName: String?,
    val groupCol: Int?,
    val groupTasks: List<Task>? = null
) {

    //  colors that can be used by groupColor
    companion object {
        val groupColors = listOf(
            RedOrange, RedPink, BabyBlue, Violet, LightGreen
        )
    }
}
