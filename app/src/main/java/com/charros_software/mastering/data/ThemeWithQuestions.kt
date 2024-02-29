package com.charros_software.mastering.data

import androidx.room.Embedded
import androidx.room.Relation

data class ThemeWithQuestions(
    @Embedded val theme: Theme,
    @Relation(
        parentColumn = "themeId",
        entityColumn = "theme_id"
    )
    val questions: List<Question>
)
