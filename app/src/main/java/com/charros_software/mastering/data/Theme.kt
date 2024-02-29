package com.charros_software.mastering.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme")
data class Theme(
    @PrimaryKey val themeId: Int,
    @ColumnInfo(name = "theme_name") val themeName: String,
    @ColumnInfo(name = "subject_id") val subjectId: Int,
)
