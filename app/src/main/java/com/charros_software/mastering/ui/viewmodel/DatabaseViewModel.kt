package com.charros_software.mastering.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charros_software.mastering.data.AppDatabase
import com.charros_software.mastering.data.MasteringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class Option {
    None, Section, Subject, Theme
}

class DatabaseViewModel(context: Context) : ViewModel() {
    private val _uiState = MutableStateFlow(DatabaseUiState())
    val uiState: StateFlow<DatabaseUiState> = _uiState.asStateFlow()

    private val database = AppDatabase.getDatabase(context)
    private val repository = MasteringRepository(
        database.sectionDao(),
        database.statProgressDao(),
        database.subjectDao(),
        database.themeDao(),
        database.questionDao())
    private var selectedOption = Option.None

    fun loadSectionList() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                selectedOption = Option.Section
                currentState.copy(optionsList = repository.getAllSections().map { it.sectionName })
            }
        }
    }

    fun setSectionSelected(section: String) {
        _uiState.update { it.copy(sectionSelected = section) }
    }

    fun loadSubjectList() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                selectedOption = Option.Subject
                if (_uiState.value.sectionSelected.isNotEmpty()) {
                    val subjectsList = repository.getSubjectsBySection(_uiState.value.sectionSelected)
                    subjectsList.forEach { println(it.subjectName) }
                    currentState.copy(optionsList = subjectsList.map { it.subjectName })
                } else { currentState.copy(optionsList = emptyList())}
            }
        }
    }

    fun setSubjectSelected(subject: String) {
        _uiState.update { it.copy(subjectSelected = subject) }
    }

    fun loadThemeList() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                selectedOption = Option.Theme
                if (_uiState.value.subjectSelected.isNotEmpty()) {
                    val themesList = repository.getThemesBySubject(_uiState.value.subjectSelected)
                    currentState.copy(optionsList = themesList.map { it.themeName} )
                } else { currentState.copy(optionsList = emptyList()) }
            }
        }
    }

    fun setThemeSelected(theme: String) {
        _uiState.update { it.copy(themeSelected = theme) }
    }

    fun addNewEntry(new: String) {
        viewModelScope.launch {
            when (selectedOption) {
                Option.Section -> {
                    repository.addNewSection(new)
                    _uiState.update { it.copy(sectionSelected = new) }
                }
                Option.None -> {
                    println("Nothing")
                }
                Option.Subject -> {
                    val sectionId =
                        repository.getSectionId(_uiState.value.sectionSelected)
                    repository.addNewSubject(new, sectionId)
                    _uiState.update { it.copy(subjectSelected = new) }
                }
                Option.Theme -> {
                    val subjectId =
                        repository.getSubjectId(_uiState.value.subjectSelected)
                    repository.addNewTheme(new, subjectId)
                    _uiState.update { it.copy(themeSelected = new) }
                }
            }
        }
    }

    fun saveNewQuestion(
        theme: String, question: String, answer1: String, answer2: String, answer3: String
    ) {
        viewModelScope.launch {
            repository.addNewQuestion(theme, question, answer1, answer2, answer3)
            _uiState.update { it.copy(questionInserted = true) }
        }
    }

    fun questionInsertionFinished() {
        _uiState.update { it.copy(questionInserted = false) }
    }
}