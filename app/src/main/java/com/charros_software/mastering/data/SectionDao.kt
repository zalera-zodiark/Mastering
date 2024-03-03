package com.charros_software.mastering.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface SectionDao {
    @Query(
        "SELECT * FROM section"
    ) suspend fun getAllSections(): List<Section>

    @Query(
        "SELECT * FROM section WHERE sectionId = :sectionId"
    ) suspend fun getSectionById(sectionId: Int): Section

    @Query(
        "INSERT INTO section (section_name) VALUES (:section)"
    ) suspend fun addSection(section: String)

    @Query(
        "SELECT sectionId FROM section WHERE section_name = :sectionName"
    ) suspend fun getSectionId(sectionName: String): Int

    @Transaction
    @Query(
        "SELECT * FROM section"
    ) suspend fun getSectionsWithSubjects(): List<SectionWithSubjects>
}