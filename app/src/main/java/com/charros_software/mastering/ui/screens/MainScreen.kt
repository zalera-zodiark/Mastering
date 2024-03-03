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
import com.charros_software.mastering.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = MainViewModel(LocalContext.current)
) {
    val mainUiState by mainViewModel.uiState.collectAsState()
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
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_database_24),
                            contentDescription = "Menu añadir datos"
                        )
                    }
                }
            )
        }
    ) {
        val sections = mainUiState.sectionList
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(top = 80.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(sections.size) {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
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
                        FilledIconButton(
                            onClick = { navController.navigate(AppScreens.MainQuestionnariesScreen.route + "/${sections[it]}") }
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