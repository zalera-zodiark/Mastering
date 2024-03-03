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

class MainQuestionnariesViewModel(context: Context): ViewModel() {

    private val _uiState = MutableStateFlow(MainQuestionnariesUiState())
    val uiState: StateFlow<MainQuestionnariesUiState> = _uiState.asStateFlow()
    private val database = AppDatabase.getDatabase(context)
    private val repository = MasteringRepository(
        database.sectionDao(),
        database.statProgressDao(),
        database.subjectDao(),
        database.themeDao(),
        database.questionDao()
    )

    fun setSubjectListBySection(section: String) {
        viewModelScope.launch {
            val subjects = repository.getSubjectsBySection(section)
            _uiState.update { currentState -> currentState.copy(subjectList = subjects.map { it.subjectName }) }
        }
    }
}