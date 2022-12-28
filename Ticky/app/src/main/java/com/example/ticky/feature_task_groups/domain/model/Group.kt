package com.example.ticky.feature_task_groups.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.ticky.feature_tasks.domain.model.Task
import com.example.ticky.ui.theme.*

data class Group(
    val groupName: String?,
    val groupCol: Int?,
    val groupTasks: List<Task>? = null
) {

    constructor() : this(null, null, null)

    //  colors that can be used by groupColor
    companion object {
        val groupColorsInt = listOf(
            RedOrange.toArgb(),
            RedOrange2.toArgb(),
            RedOrange3.toArgb(),
            RedOrange4.toArgb(),
            RedPink.toArgb(),
            RedPink2.toArgb(),
            RedPink3.toArgb(),
            RedPink4.toArgb(),
            BabyBlue.toArgb(),
            BabyBlue2.toArgb(),
            BabyBlue3.toArgb(),
            BabyBlue4.toArgb(),
            Violet.toArgb(),
            Violet2.toArgb(),
            Violet3.toArgb(),
            Violet4.toArgb(),
            LightGreen.toArgb(),
            LightGreen2.toArgb(),
            LightGreen3.toArgb(),
            LightGreen4.toArgb()
        )
    }
}
