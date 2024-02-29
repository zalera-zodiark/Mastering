package com.charros_software.mastering.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat_progress")
data class StatProgress(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "section") val section: String,
    @ColumnInfo(name = "subject") val subject: String,
    @ColumnInfo(name = "theme") val theme: String,
    @ColumnInfo(name = "mark_goal") val markGoal : Int,
)