package com.charros_software.mastering.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StatProgressDao {
    @Query("SELECT * FROM stat_progress WHERE uid = :statId")
    fun getStatProgressById(statId: Int): StatProgress

    @Query("SELECT * FROM stat_progress WHERE " +
            "section = :section AND subject = :subject AND theme = :theme")
    fun getStatProgressByData(section:String, subject: String, theme: String): StatProgress
}