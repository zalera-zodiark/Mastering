package com.charros_software.mastering.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.charros_software.mastering.data.AppDatabase
import com.charros_software.mastering.data.MasteringRepository
import com.charros_software.mastering.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PracticeQuestionaryViewModel(context: Context, val theme: String?): ViewModel() {

    private val _uiState = MutableStateFlow(PracticeQuestionaryUiState())
    val uiState: StateFlow<PracticeQuestionaryUiState> = _uiState.asStateFlow()

    private val database = AppDatabase.getDatabase(context)
    private val repository = MasteringRepository(
        database.sectionDao(),
        database.statProgressDao(),
        database.subjectDao(),
        database.themeDao(),
        database.questionDao())
    private lateinit var listOfQuestions: List<Question>
    private var currentIndex = 0
    private var currentGoal = 0

    init {
        viewModelScope.launch {
            listOfQuestions = repository.getQuestionsByTheme(theme ?: "")
            val firstQuestion = listOfQuestions.first()
            val listOfAnswer = listOf(firstQuestion.answer1, firstQuestion.answer2, firstQuestion.answer3)
            val listShuffled = listOfAnswer.shuffled()
            _uiState.update { currentState ->
                currentState.copy(
                    questionP = firstQuestion.questionP,
                    randomAnswer1 = listShuffled[0],
                    randomAnswer2 = listShuffled[1],
                    randomAnswer3 = listShuffled[2]
                )
            }
        }
    }

    fun selectAnswer(selection: Int) {
        val currentQuestion = listOfQuestions[currentIndex]
        when (selection) {
            1 -> {
                if (uiState.value.randomAnswer1.equals(currentQuestion.answer1, true)) {
                    _uiState.update { it.copy(enabledNextQuestion = true, correctAnswer = true) }
                    currentGoal++
                } else {
                    _uiState.update { it.copy(enabledNextQuestion = true, correctAnswer = false) }
                }
            }
            2 -> {
                if (uiState.value.randomAnswer2.equals(currentQuestion.answer1, true)) {
                    _uiState.update { it.copy(enabledNextQuestion = true, correctAnswer = true) }
                    currentGoal++
                } else {
                    _uiState.update { it.copy(enabledNextQuestion = true, correctAnswer = false) }
                }
            }
            3 -> {
                if (uiState.value.randomAnswer1.equals(currentQuestion.answer3, true)) {
                    _uiState.update { it.copy(enabledNextQuestion = true, correctAnswer = true) }
                    currentGoal++
                } else {
                    _uiState.update { it.copy(enabledNextQuestion = true, correctAnswer = false) }
                }
            }
        }
    }

    fun nextQuestion() {
        currentIndex++
        if (currentIndex < listOfQuestions.size) {
            val question = listOfQuestions[currentIndex]
            val listOfAnswer = listOf(question.answer1, question.answer2, question.answer3)
            val listShuffled = listOfAnswer.shuffled()
            _uiState.update { currentState ->
                currentState.copy(
                    questionP = question.questionP,
                    randomAnswer1 = listShuffled[0],
                    randomAnswer2 = listShuffled[1],
                    randomAnswer3 = listShuffled[2],
                    enabledNextQuestion = false,
                )
            }
        } else {
            _uiState.update { it.copy(finishedQuestionary = true, enabledNextQuestion = false, resultQuestionary = ((currentGoal * 100) / listOfQuestions.size).toFloat()) }
            viewModelScope.launch {
                repository.saveProgress(theme, ((currentGoal * 100) / listOfQuestions.size))
            }
        }
    }
}