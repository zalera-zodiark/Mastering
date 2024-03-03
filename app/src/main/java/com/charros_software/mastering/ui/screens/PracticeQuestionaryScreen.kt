package com.charros_software.mastering.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.charros_software.mastering.ui.viewmodel.PracticeQuestionaryUiState
import com.charros_software.mastering.ui.viewmodel.PracticeQuestionaryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun practiceQuestionaryScreen(
    navController: NavController, theme: String?,
    practiceQuestionaryViewModel: PracticeQuestionaryViewModel = PracticeQuestionaryViewModel(
        LocalContext.current, theme)
) {
    val practiceQuestionaryUiState by practiceQuestionaryViewModel.uiState.collectAsState()

    Scaffold {
        practiceQuestionaryContent(practiceQuestionaryViewModel, practiceQuestionaryUiState, navController)
    }
}

@Composable
fun practiceQuestionaryContent(
    practiceQuestionaryViewModel: PracticeQuestionaryViewModel,
    practiceQuestionaryUiState: PracticeQuestionaryUiState,
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp, start = 2.dp, end = 2.dp),
            text = practiceQuestionaryUiState.questionP
        )
        OutlinedButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
            onClick = { practiceQuestionaryViewModel.selectAnswer(1) }
        ) { Text(text = practiceQuestionaryUiState.randomAnswer1) }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
            onClick = { practiceQuestionaryViewModel.selectAnswer(2) }
        ) { Text(text = practiceQuestionaryUiState.randomAnswer2) }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 6.dp),
            onClick = { practiceQuestionaryViewModel.selectAnswer(3) }
        ) { Text(text = practiceQuestionaryUiState.randomAnswer3) }

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp, start = 4.dp, end = 8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = { practiceQuestionaryViewModel.nextQuestion() },
                enabled = practiceQuestionaryUiState.enabledNextQuestion
            ) { Text("Siguiente") }
        }

        if (practiceQuestionaryUiState.enabledNextQuestion) {
            if (practiceQuestionaryUiState.correctAnswer) {
                Toast.makeText(LocalContext.current, "Respuesta correcta!!!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(LocalContext.current, "Respuesta incorrecta!!!", Toast.LENGTH_SHORT).show()
            }
        }
        if (practiceQuestionaryUiState.finishedQuestionary) {
            Toast.makeText(LocalContext.current, practiceQuestionaryUiState.resultQuestionary.toString(), Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }
    }
}