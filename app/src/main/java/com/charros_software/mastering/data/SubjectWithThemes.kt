package com.charros_software.mastering.data

import androidx.room.Embedded
import androidx.room.Relation

data class SubjectWithThemes(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subjectId",
        entityColumn = "subject_id"
    )
    val themes: List<Theme>
)
