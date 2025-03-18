package com.grigorii.couponsapp.compose.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


val options = listOf("Шаг 1", "Шаг 2", "Шаг 3")

@Composable
fun GreetingsScreen(
    modifier: Modifier = Modifier,
    mainNavController: NavController,
    stateToNavigate: String
) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = options.first()) {
        options.forEach { step ->
            composable(step) {
                when (step) {
                    options[0] -> StepOneScreen(navController = navController)
                    options[1] -> StepTwoScreen(navController = navController)
                    options[2] -> StepThreeScreen(
                        navController = navController,
                        mainNavController = mainNavController,
                        stateToNavigate = stateToNavigate
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepOneScreen(modifier: Modifier = Modifier, navController: NavController) {
    var searchText by rememberSaveable { mutableStateOf("") }

    var expanded by remember { mutableStateOf(true) }

    val townsSet: Set<String> = setOf(
        "Архангельск",
        "Астрахань",
        "Балашиха",
        "Барнаул",
        "Благовещенск",
        "1Благовещенск1",
        "2Благовещенск2",
        "3Благовещенск3",
        "4Благовещенск4",
        "Томск",
        "Tomsk"
    )

    val filteredCities = remember(searchText) {
        if (searchText.isEmpty()) {
            townsSet
        } else townsSet.filter { city ->
            city.contains(searchText, ignoreCase = true)
        }.take(5)
    }

    LazyColumn(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                HeaderSection(title = "Здравствуйте!")

                Text(
                    "Выберите ваш город",
                    modifier = Modifier.padding(top = 32.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Text(
                    "Город",
                    modifier = Modifier.padding(top = 32.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                ExposedDropdownMenuBox(
                    expanded = false,
                    onExpandedChange = {
                        expanded = it
                    }
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            expanded = true
                        },
                        readOnly = false,
                        maxLines = 1,
                        label = { Text("") },
                        trailingIcon = {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Clear",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )

                    ExposedDropdownMenu(
                        modifier = Modifier.height(280.dp),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        filteredCities.forEach { city ->
                            DropdownMenuItem(
                                modifier = Modifier.height(56.dp),
                                text = {
                                    Text(
                                        city,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Normal
                                    )

                                },
                                onClick = {
                                    searchText = city
                                    expanded = true
                                }
                            )
                        }
                    }
                }


                AnimatedVisibility(
                    visible = true,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    Spacer(
                        modifier = Modifier.height(280.dp)
                    )
                }


                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    onClick = { navController.navigate(options[1]) }
                ) {
                    Text("Применить")
                }

                StepSegmentedButton(
                    modifier = Modifier.padding(
                        top = 42.dp,
                        bottom = 88.dp
                    ),
                    navController = navController,
                    selected = options[0]
                )
            }
        }
    }
}


@Composable
fun StepTwoScreen(modifier: Modifier = Modifier, navController: NavController) {
    var name by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                HeaderSection(title = "Здравствуйте!")

                Text(
                    "Введите ваше имя",
                    modifier = Modifier.padding(top = 32.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Text(
                    "Имя",
                    modifier = Modifier.padding(top = 32.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    readOnly = false,
                    maxLines = 1,
                    label = { Text("") },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )


                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    onClick = { navController.navigate(options[2]) }
                ) {
                    Text("Применить")
                }

                StepSegmentedButton(
                    modifier = Modifier.padding(
                        top = 42.dp,
                        bottom = 88.dp
                    ),
                    navController = navController,
                    selected = options[1]
                )
            }
        }
    }
}


@Composable
fun StepThreeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mainNavController: NavController,
    stateToNavigate: String
) {
    var surname by rememberSaveable { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                HeaderSection(title = "Здравствуйте!")

                Text(
                    "Введите вашу фамалию",
                    modifier = Modifier.padding(top = 32.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Text(
                    "Фамилия",
                    modifier = Modifier.padding(top = 32.dp),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = surname,
                    onValueChange = {
                        surname = it
                    },
                    readOnly = false,
                    maxLines = 1,
                    label = { Text("") },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )


                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    onClick = { /* переход на главную при условии что поля заполнены */
                        mainNavController.navigate(stateToNavigate)
                    }
                ) {
                    Text("Применить")
                }

                StepSegmentedButton(
                    modifier = Modifier.padding(
                        top = 42.dp,
                        bottom = 88.dp
                    ),
                    navController = navController,
                    selected = options[2]
                )
            }
        }
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier, title: String) {
    Row(
        modifier = modifier
            .background(Color(0xFFFFFBFE))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            title,
            style = TextStyle(
                color = Color(0xFF1C1B1F),
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp
            ),
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun StepSegmentedButton(
    modifier: Modifier = Modifier,
    selected: String,
    navController: NavController
) {
    var selectedIndex by remember { mutableIntStateOf(0) }


    SingleChoiceSegmentedButtonRow(
        modifier = modifier.fillMaxWidth()
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                modifier = Modifier.height(40.dp),
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = {
                    selectedIndex = index
                    navController.navigate(options[index])
                },
                selected = label == selected,
                label = { Text(label) }
            )
        }
    }
}

