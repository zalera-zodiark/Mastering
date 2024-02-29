package com.charros_software.mastering.data

class MasteringRepository(
    private val sectionDao: SectionDao,
    private val statProgressDao: StatProgressDao,
    private val subjectDao: SubjectDao,
    private val themeDao: ThemeDao
) {

    suspend fun getAllSections() = sectionDao.getAllSections()

    suspend fun getSectionId(section: String) = sectionDao.getSectionId(section)

    suspend fun getSubjectId(subject: String) = subjectDao.getSubjectId(subject)

    suspend fun getThemeId(theme: String) = themeDao.getThemeId(theme)

    suspend fun getSubjectsBySection(section: String): List<Subject> {
        val sectionsWithSubjects = sectionDao.getSectionsWithSubjects()
        val subjectList = sectionsWithSubjects.find { it.section.sectionName.equals(section, true) }
        return subjectList?.subjects ?: emptyList()
    }

    suspend fun getThemesBySubject(subject: String): List<Theme> {
        val subjectsWithThemes = subjectDao.getSubjectsWithThemes()
        val themeList = subjectsWithThemes.find { it.subject.subjectName.equals(subject, true) }
        return themeList?.themes ?: emptyList()
    }

    suspend fun addNewSection(section: String) {
        sectionDao.addSection(section)
    }

    suspend fun addNewSubject(subject: String, sectionId: Int) {
        subjectDao.addSubject(subject, sectionId)
    }

    suspend fun addNewTheme(theme: String, subjectId: Int) {
        themeDao.addTheme(theme, subjectId)
    }
}