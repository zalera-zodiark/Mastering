package com.charros_software.mastering.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.charros_software.mastering.R
import com.charros_software.mastering.navigation.AppScreens

@Composable
fun getQuestionnaries(theme: String?):List<String> {
    return when (theme?.toInt()) {
        0 -> stringArrayResource(R.array.array_of_themes_inkscape).toList()

        else -> emptyList()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun questionaryScreen(navController: NavController, theme: String?) {
    Scaffold {

        val listOfThemes = getQuestionnaries(theme)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 20.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(listOfThemes.size) {
                ElevatedCard(
                    modifier = Modifier.size(width = 380.dp, height = 150.dp).padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
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
                                onClick = { navController.navigate(AppScreens.PracticeQuestionaryScreen.route + "/${it}") }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_smart_play_24),
                                    contentDescription = "Cuestionario"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}