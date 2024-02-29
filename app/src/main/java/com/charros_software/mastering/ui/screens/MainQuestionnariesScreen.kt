package com.charros_software.mastering.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.charros_software.mastering.R
import com.charros_software.mastering.navigation.AppScreens

@Composable
fun getSubjects(section: String?):List<String> {
    return when (section?.toInt()) {
        0 -> { //Ingeniería en computación
            stringArrayResource(R.array.array_of_subjects_computer_engineering).toList()
        }
        1 -> { // Medicina
            stringArrayResource(R.array.array_of_subjects_medicine).toList()
        }
        2 -> { // Ingeniería en Electrónica
            stringArrayResource(R.array.array_of_subjects_electronic_engineering).toList()
        }
        3 -> { // Mecánica Automotriz
            stringArrayResource(R.array.array_of_subjects_automotive).toList()
        }
        4 -> { // Supervivencia
            stringArrayResource(R.array.array_of_subjects_survival).toList()
        }
        5 -> { // Enfermería
            stringArrayResource(R.array.array_of_subjects_nursing).toList()
        }
        else -> emptyList()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainQuestionnariesScreen(navController: NavController, section: String?) {
    Scaffold {

        val listOfSubjects = getSubjects(section)
            LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 20.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listOfSubjects.size) {
                ElevatedCard(
                    modifier = Modifier.size(width = 380.dp, height = 150.dp).padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TitleText(listOfSubjects[it])
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            FilledIconButton(
                                onClick = { navController.navigate(AppScreens.QuestionaryScreen.route + "/${it}") }
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