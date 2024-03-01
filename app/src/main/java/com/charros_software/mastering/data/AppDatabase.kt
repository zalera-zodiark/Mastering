package com.charros_software.mastering.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [StatProgress::class, Question::class, Section::class, Subject::class, Theme::class, Question::class],
    version = 5)
abstract class AppDatabase: RoomDatabase() {
    abstract fun statProgressDao(): StatProgressDao
    abstract fun sectionDao(): SectionDao
    abstract fun subjectDao(): SubjectDao
    abstract fun themeDao(): ThemeDao
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mastering_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}