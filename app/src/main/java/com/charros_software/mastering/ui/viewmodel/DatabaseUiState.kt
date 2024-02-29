package com.charros_software.mastering.ui.viewmodel

data class DatabaseUiState(
    val optionsList: List<String> = emptyList(),
    val sectionSelected: String = "",
    val subjectSelected: String = "",
    val themeSelected: String = "",
)