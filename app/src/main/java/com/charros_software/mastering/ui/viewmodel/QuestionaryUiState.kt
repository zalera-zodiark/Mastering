package com.charros_software.mastering.ui.viewmodel

import com.charros_software.mastering.data.StatProgress

data class QuestionaryUiState(
    val themeList: List<String> = emptyList(),
    val statProgressList: List<StatProgress> = emptyList()
)