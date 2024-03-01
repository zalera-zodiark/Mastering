package com.charros_software.mastering.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuestionDao {

    @Query(
        "INSERT INTO question_t(question_p, answer1, answer2, answer3, theme_id) " +
                "VALUES(:question, :answer1, :answer2, :answer3, :themeId)"
    ) suspend fun addQuestion(question: String, answer1: String, answer2: String, answer3: String, themeId: Int)
}