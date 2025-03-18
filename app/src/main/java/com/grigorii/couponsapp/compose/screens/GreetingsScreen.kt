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

@Composable
fun GreetingsScreen(modifier: Modifier = Modifier) {
    // StepOneScreen()

    // StepTwoScreen()

    StepThreeScreen()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepOneScreen(modifier: Modifier = Modifier) {
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
                    onClick = { }
                ) {
                    Text("Применить")
                }

                StepSegmentedButton(
                    modifier = Modifier.padding(
                        top = 42.dp,
                        bottom = 88.dp
                    )
                )
            }
        }
    }
}


@Composable
fun StepTwoScreen(modifier: Modifier = Modifier) {
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
                    onClick = { }
                ) {
                    Text("Применить")
                }

                StepSegmentedButton(
                    modifier = Modifier.padding(
                        top = 42.dp,
                        bottom = 88.dp
                    )
                )
            }
        }
    }
}


@Composable
fun StepThreeScreen(modifier: Modifier = Modifier) {
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
                    "Введите ваше имя",
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
                    onClick = { }
                ) {
                    Text("Применить")
                }

                StepSegmentedButton(
                    modifier = Modifier.padding(
                        top = 42.dp,
                        bottom = 88.dp
                    )
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
fun StepSegmentedButton(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Шаг 1", "Шаг 2", "Шаг 3")

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
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(label) }
            )
        }
    }
}

