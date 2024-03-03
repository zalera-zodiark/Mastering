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

class MainViewModel(context: Context): ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()
    private val database = AppDatabase.getDatabase(context)
    private val repository = MasteringRepository(
        database.sectionDao(),
        database.statProgressDao(),
        database.subjectDao(),
        database.themeDao(),
        database.questionDao()
    )

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(sectionList = repository.getAllSections().map { it.sectionName })
            }
        }
    }
}