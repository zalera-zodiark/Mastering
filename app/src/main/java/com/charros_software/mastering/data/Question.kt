package com.charros_software.mastering.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_t")
data class Question(
    @PrimaryKey(autoGenerate = true) val questionId: Int,
    @ColumnInfo(name = "question_p") val questionP: String,
    @ColumnInfo(name = "answer1") val answer1: String,
    @ColumnInfo(name = "answer2") val answer2: String,
    @ColumnInfo(name = "answer3") val answer3: String,
    @ColumnInfo(name = "theme_id") val themeId: Int,
)
