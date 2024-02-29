package com.charros_software.mastering.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "section")
data class Section(
    @PrimaryKey(autoGenerate = true) val sectionId: Int,
    @ColumnInfo(name = "section_name") val sectionName: String
)
