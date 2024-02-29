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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = { Text("Mastering") },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(AppScreens.DatabaseScreen.route) }
                    ) { Icon(
                        painter = painterResource(R.drawable.ic_database_24),
                        contentDescription = "Menu añadir datos")
                    }
                }
            )
        }
    ) {
        val sections = stringArrayResource(R.array.array_of_sections)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 80.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(sections.size) {
                ElevatedCard(
                    modifier = Modifier.size(width = 380.dp, height = 150.dp).padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
                            text = sections[it],
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
                                onClick = { navController.navigate(AppScreens.MainTopicsScreen.route + "/${it}") }
                            ) {
                                Icon(painter = painterResource(R.drawable.ic_book_24),
                                    contentDescription = "Teoría")
                            }
                            FilledIconButton(
                                onClick = { navController.navigate(AppScreens.MainQuestionnariesScreen.route + "/${it}") }
                            ) {
                                Icon(painter = painterResource(R.drawable.ic_question_24),
                                    contentDescription = "Teoría")
                            }
                        }
                    }
                }
            }
        }
    }
}