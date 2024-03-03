package com.charros_software.mastering.ui.viewmodel

data class PracticeQuestionaryUiState(
    val questionP: String = "",
    val randomAnswer1: String = "",
    val randomAnswer2: String = "",
    val randomAnswer3: String = "",
    val enabledNextQuestion: Boolean = false,
    val correctAnswer: Boolean = false,
    val finishedQuestionary: Boolean = false,
    val resultQuestionary: Float = 0f
)