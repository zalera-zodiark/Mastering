package com.charros_software.mastering.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StatProgressDao {
    @Query(
        "SELECT * FROM stat_progress WHERE subject = :subject"
    ) suspend fun getStatProgressListBySubject(subject: String): List<StatProgress>

    @Query(
        "INSERT INTO stat_progress(section, subject, theme, mark_goal) " +
                "VALUES(:section, :subject, :theme, :markGoal)"
    ) suspend fun newStatProgress(section: String, subject: String, theme: String, markGoal: Int)

    @Query("UPDATE stat_progress SET mark_goal = :markGoal WHERE uid = :statId")
    suspend fun updateStatProgress(statId: Int, markGoal: Int)

    @Query("SELECT * FROM stat_progress WHERE uid = :statId")
    suspend fun getStatProgressById(statId: Int): StatProgress

    @Query("SELECT * FROM stat_progress WHERE " +
            "theme = :theme")
    suspend fun getStatProgressByData(theme: String): StatProgress?
}