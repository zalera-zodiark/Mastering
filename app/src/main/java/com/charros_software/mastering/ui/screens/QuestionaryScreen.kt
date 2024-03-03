package com.charros_software.mastering.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.charros_software.mastering.R
import com.charros_software.mastering.navigation.AppScreens
import com.charros_software.mastering.ui.viewmodel.QuestionaryViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun questionaryScreen(
    navController: NavController, subject: String?,
    questionaryViewModel: QuestionaryViewModel = QuestionaryViewModel(LocalContext.current)) {

    val questionaryUiState by questionaryViewModel.uiState.collectAsState()
    questionaryViewModel.loadThemeList(subject ?: "")

    Scaffold {

        val listOfThemes = questionaryUiState.themeList
        val listOfStatProgress = questionaryUiState.statProgressList
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 20.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listOfThemes.size) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                            text = listOfThemes[it],
                            textAlign = TextAlign.Left,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                            lineHeight = MaterialTheme.typography.titleLarge.lineHeight,
                            letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                        )
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            FilledIconButton(
                                onClick = { navController.navigate(AppScreens.PracticeQuestionaryScreen.route + "/${listOfThemes[it]}") }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_smart_play_24),
                                    contentDescription = "Cuestionario"
                                )
                            }
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = listOfStatProgress.find {
                                    statProgress -> statProgress.theme.equals(listOfThemes[it], true)
                                }?.markGoal.toString() + "%"
                            )
                        }
                    }
                }
            }
        }
    }
}