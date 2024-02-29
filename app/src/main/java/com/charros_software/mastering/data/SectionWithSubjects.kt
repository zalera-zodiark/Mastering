package com.charros_software.mastering.data

import androidx.room.Embedded
import androidx.room.Relation

data class SectionWithSubjects(
    @Embedded val section: Section,
    @Relation(
        parentColumn = "sectionId",
        entityColumn = "section_id"
    )
    val subjects: List<Subject>
)
