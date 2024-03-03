package com.charros_software.mastering.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.charros_software.mastering.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            mainScreen(navController)
        }
        composable(route = AppScreens.MainQuestionnariesScreen.route + "/{section}",
            arguments = listOf(navArgument(name = "section") {
                type = NavType.StringType
            })) {
            mainQuestionnariesScreen(navController, it.arguments?.getString("section"))
        }
        composable(route = AppScreens.QuestionaryScreen.route + "/{theme}",
            arguments = listOf(navArgument(name = "theme") {
                type = NavType.StringType
            })) {
            questionaryScreen(navController, it.arguments?.getString("theme"))
        }
        composable(route = AppScreens.PracticeQuestionaryScreen.route + "/{chapter}",
            arguments = listOf(navArgument(name = "chapter") {
                type = NavType.StringType
            })) {
            practiceQuestionaryScreen(navController, it.arguments?.getString("chapter"))
        }
        composable(route = AppScreens.DatabaseScreen.route) {
            databaseScreen(navController)
        }
    }
}