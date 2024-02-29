package com.charros_software.mastering.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subject")
data class Subject(
    @PrimaryKey(autoGenerate = true) val subjectId: Int,
    @ColumnInfo(name = "subject_name") val subjectName: String,
    @ColumnInfo(name = "section_id") val sectionId: Int
)
