package com.charros_software.mastering.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.charros_software.mastering.R
import com.charros_software.mastering.ui.viewmodel.DatabaseViewModel
import com.charros_software.mastering.ui.viewmodel.Option

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun databaseScreen(
    navController: NavController,
    databaseViewModel: DatabaseViewModel = DatabaseViewModel(
        LocalContext.current
    )
) {
    val databaseUiState by databaseViewModel.uiState.collectAsState()
    var expandCardData by remember { mutableStateOf(false) }
    var newEntry by rememberSaveable { mutableStateOf("") }
    var sectionSelected by rememberSaveable { mutableStateOf("No seleccionada") }
    var subjectSelected by rememberSaveable { mutableStateOf("No seleccionada") }
    var themeSelected by rememberSaveable { mutableStateOf("No seleccionada") }
    var optionSelected by rememberSaveable { mutableStateOf(Option.None) }
    var questionText by rememberSaveable { mutableStateOf("") }
    var answerText1 by rememberSaveable { mutableStateOf("") }
    var answerText2 by rememberSaveable { mutableStateOf("") }
    var answerText3 by rememberSaveable { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            OutlinedCard(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TitleText("Datos")
                    IconButton(
                        onClick = { expandCardData = !expandCardData }
                    ) {
                        Icon(painter = painterResource(
                            if (expandCardData) R.drawable.ic_arrow_up_24 else R.drawable.ic_arrow_down_24
                        ), "Arrow for expand card")
                    }
                }
                AnimatedVisibility(
                    visible = expandCardData,
                    enter = fadeIn(initialAlpha = 0.4f),
                    exit = fadeOut(animationSpec = tween(durationMillis = 250))
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
                    ) {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Sección: ")
                                Text(sectionSelected)
                                ElevatedButton(
                                    modifier = Modifier.padding(start = 10.dp),
                                    onClick = {
                                        showBottomSheet = true
                                        optionSelected = Option.Section
                                        databaseViewModel.loadSectionList()
                                        subjectSelected = ""
                                        themeSelected = ""
                                    } ) { Text("Seleccionar") }
                            }
                        }
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Materia: ")
                                Text(subjectSelected)
                                ElevatedButton(
                                    modifier = Modifier.padding(start = 10.dp),
                                    onClick = {
                                        showBottomSheet = true
                                        optionSelected = Option.Subject
                                        databaseViewModel.loadSubjectList()
                                        themeSelected = ""
                                    }) { Text("Seleccionar") }
                            }
                        }
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Tema: ")
                                Text(themeSelected)
                                ElevatedButton(
                                    modifier = Modifier.padding(start = 10.dp),
                                    onClick = {
                                        showBottomSheet = true
                                        optionSelected = Option.Theme
                                        databaseViewModel.loadThemeList()
                                    }) { Text("Seleccionar") }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 6.dp),
                    value = questionText,
                    onValueChange = { questionText = it },
                    label = { Text("pregunta") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 6.dp),
                    value = answerText1,
                    onValueChange = { answerText1 = it },
                    label = { Text("respuesta-1") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 6.dp),
                    value = answerText2,
                    onValueChange = { answerText2 = it },
                    label = { Text("respuesta-2") },
                    singleLine = true
                )
                TextField(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 6.dp),
                    value = answerText3,
                    onValueChange = { answerText3 = it },
                    label = { Text("respuesta-3") },
                    singleLine = true
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp, start = 4.dp, end = 4.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedButton(
                        modifier = Modifier.padding(end = 8.dp),
                        onClick = { navController.popBackStack() }
                    ) { Text("Cancelar") }
                    Button(
                        modifier = Modifier.padding(end = 4.dp),
                        onClick = {
                            databaseViewModel
                                .saveNewQuestion(themeSelected, questionText, answerText1, answerText2, answerText3)
                            questionText = ""
                            answerText1 = ""
                            answerText2 = ""
                            answerText3 = ""
                        }
                    ) { Text("Aceptar") }
                }
            }

        }
        if (databaseUiState.questionInserted) {
            Toast.makeText(LocalContext.current, "Pregunta insertada con éxito!!!", Toast.LENGTH_SHORT).show()
            databaseViewModel.questionInsertionFinished()
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.padding(bottom = 8.dp),
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextField(
                        modifier = Modifier.padding(horizontal = 2.dp, vertical = 4.dp),
                        value = newEntry,
                        onValueChange = { newEntry = it },
                        label = { Text("Nuevo") },
                        singleLine = true
                    )
                    Button(
                        onClick = {
                            databaseViewModel.addNewEntry(newEntry)
                            showBottomSheet = false
                            when (optionSelected) {
                                Option.Section -> sectionSelected = newEntry
                                Option.Subject -> subjectSelected = newEntry
                                Option.Theme -> themeSelected = newEntry
                                Option.None -> println("Nada")
                            }
                            newEntry = ""
                        }
                    ) { Text("Añadir Nuevo") }
                }
                Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 4.dp))
                LazyColumn {
                    items(databaseUiState.optionsList.size) {
                        ListItem(
                            headlineContent = { Text(databaseUiState.optionsList[it])},
                            trailingContent = { IconButton(onClick = {
                                showBottomSheet = false
                                when (optionSelected) {
                                    Option.Section -> {
                                        databaseViewModel.setSectionSelected(databaseUiState.optionsList[it])
                                        sectionSelected = databaseUiState.optionsList[it]
                                    }
                                    Option.Subject -> {
                                        databaseViewModel.setSubjectSelected(databaseUiState.optionsList[it])
                                        subjectSelected = databaseUiState.optionsList[it]
                                    }
                                    Option.Theme -> {
                                        databaseViewModel.setThemeSelected(databaseUiState.optionsList[it])
                                        themeSelected = databaseUiState.optionsList[it]
                                    }
                                    Option.None -> println("Nada")
                                }
                                optionSelected = Option.None
                            }) {
                                Icon(painter = painterResource(R.drawable.ic_smart_play_24), "-")} }
                        )
                        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp, vertical = 4.dp))
                    }
                }
            }
        }
    }
}