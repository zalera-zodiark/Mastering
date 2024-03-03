package com.charros_software.mastering.data

class MasteringRepository(
    private val sectionDao: SectionDao,
    private val statProgressDao: StatProgressDao,
    private val subjectDao: SubjectDao,
    private val themeDao: ThemeDao,
    private val questionDao: QuestionDao
) {

    suspend fun getAllSections() = sectionDao.getAllSections()

    suspend fun getSectionId(section: String) = sectionDao.getSectionId(section)

    suspend fun getSubjectId(subject: String) = subjectDao.getSubjectId(subject)

    private suspend fun getThemeId(theme: String) = themeDao.getThemeId(theme)

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

    suspend fun getQuestionsByTheme(theme: String): List<Question> {
        val themesWithQuestions = themeDao.getThemesWithQuestions()
        val questionList = themesWithQuestions.find { it.theme.themeName.equals(theme, true) }
        return questionList?.questions ?: emptyList()
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

    suspend fun addNewQuestion(
        theme: String, question: String, answer1: String, answer2: String, answer3: String
    ) {
        val themeId = getThemeId(theme)
        questionDao.addQuestion(question, answer1, answer2, answer3, themeId)
    }

    suspend fun saveProgress(theme: String?, progress: Int) {
        if (theme != null) {
            if (statProgressDao.getStatProgressByData(theme) != null) {
                val idProgressStat = statProgressDao.getStatProgressByData(theme)
                statProgressDao.updateStatProgress(idProgressStat!!.uid, progress)
            } else {
                val theme = themeDao.getThemeSubjectId(theme)
                val idSubject = theme.subjectId
                val subject = subjectDao.getSubjectById(idSubject)
                val idSection = subject.sectionId
                val section = sectionDao.getSectionById(idSection)
                statProgressDao.newStatProgress(
                    section.sectionName,
                    subject.subjectName,
                    theme.themeName,
                    progress)
            }
        }
    }

    suspend fun getStatProgressList(subject: String): List<StatProgress> {
        return statProgressDao.getStatProgressListBySubject(subject)
    }
}