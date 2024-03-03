package com.charros_software.mastering.navigation

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")
    object MainQuestionnariesScreen: AppScreens("main_questionnaries_screen")
    object QuestionaryScreen: AppScreens("questionary_screen")
    object PracticeQuestionaryScreen : AppScreens("practice_questionary_screen")
    object DatabaseScreen: AppScreens("database_screen")
}