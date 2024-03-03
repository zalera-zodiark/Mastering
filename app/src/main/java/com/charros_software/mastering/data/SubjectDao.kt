package com.charros_software.mastering.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface SubjectDao {

    @Query(
        "INSERT INTO subject(subject_name, section_id) VALUES(:subject, :sectionId)"
    ) suspend fun addSubject(subject: String, sectionId: Int)

    @Query(
        "SELECT * FROM subject WHERE subjectId = :subjectId"
    ) suspend fun getSubjectById(subjectId:Int): Subject

    @Query(
        "SELECT subjectId FROM subject WHERE(subject_name = :subjectName)"
    ) suspend fun getSubjectId(subjectName: String): Int

    @Transaction
    @Query(
        "SELECT * FROM subject"
    ) suspend fun getSubjectsWithThemes(): List<SubjectWithThemes>
}