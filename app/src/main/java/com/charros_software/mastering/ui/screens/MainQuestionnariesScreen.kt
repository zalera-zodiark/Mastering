package com.charros_software.mastering.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.charros_software.mastering.R
import com.charros_software.mastering.navigation.AppScreens
import com.charros_software.mastering.ui.viewmodel.MainQuestionnariesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainQuestionnariesScreen(
    navController: NavController,
    section: String?,
    mainQuestionnariesViewModel: MainQuestionnariesViewModel = MainQuestionnariesViewModel(LocalContext.current),) {

    val mainQuestionnariesUiState by mainQuestionnariesViewModel.uiState.collectAsState()
    mainQuestionnariesViewModel.setSubjectListBySection(section ?: "")

    Scaffold {

        val listOfSubjects = mainQuestionnariesUiState.subjectList
            LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 20.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listOfSubjects.size) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TitleText(listOfSubjects[it])
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            FilledIconButton(
                                onClick = { navController.navigate(AppScreens.QuestionaryScreen.route + "/${listOfSubjects[it]}") }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_question_24),
                                    contentDescription = "Teoría"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}