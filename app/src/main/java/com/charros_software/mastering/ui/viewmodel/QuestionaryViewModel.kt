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

class QuestionaryViewModel(context: Context): ViewModel() {

    private val _uiState = MutableStateFlow(QuestionaryUiState())
    val uiState: StateFlow<QuestionaryUiState> = _uiState.asStateFlow()

    private val database = AppDatabase.getDatabase(context)
    private val repository = MasteringRepository(
        database.sectionDao(),
        database.statProgressDao(),
        database.subjectDao(),
        database.themeDao(),
        database.questionDao())

    fun loadThemeList(subject: String) {
        viewModelScope.launch {
            val themes = repository.getThemesBySubject(subject)
            val statProgressList = repository.getStatProgressList(subject)
            _uiState.update { currentState ->
                currentState.copy(themeList = themes.map { it.themeName }, statProgressList = statProgressList)
            }
        }
    }
}