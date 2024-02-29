package com.charros_software.mastering.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ThemeDao {

    @Query(
        "SELECT themeId FROM theme WHERE theme_name = :theme"
    ) suspend fun getThemeId(theme: String): Int

    @Query(
        "INSERT INTO theme(theme_name, subject_id) VALUES(:theme, :subjectId)"
    ) suspend fun addTheme(theme: String, subjectId: Int)

    @Transaction
    @Query(
        "SELECT * FROM theme"
    ) suspend fun getThemesWithChapters(): List<ThemeWithQuestions>
}