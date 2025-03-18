package com.grigorii.couponsapp.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            HeaderSection(
                title = "Фильтры"
            )

            BackButton(modifier = Modifier.padding(top = 32.dp))

            CategoriesSection()

            TownSection()

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                onClick = { }
            ) {
                Text("Применить")
            }
        }
    }

}


@Composable
fun BackButton(modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { },
        modifier = modifier.height(40.dp)
    ) {
        Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "go back",
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Назад",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun CategoriesSection(modifier: Modifier = Modifier) {
    val categories = listOf("Здоровье", "Обучение", "Фитнес", "Курсы")

    val checkedStates = remember {
        mutableStateMapOf<String, Boolean>()
    }.apply {
        categories.forEach { category ->
            if (!containsKey(category)) {
                this[category] = true
            }
        }
    }

    Text(
        "Категории",
        modifier = Modifier.padding(top = 32.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
        )
    )

    LazyColumn(modifier = Modifier.padding(top = 16.dp).height(170.dp)) {
        item {
            categories.forEach { label ->
                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        label,
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(1f),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Checkbox(
                        modifier = Modifier.padding(8.dp),
                        checked = checkedStates[label] ?: false,
                        onCheckedChange = { isChecked ->
                            checkedStates[label] = isChecked
                        }
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TownSection(modifier: Modifier = Modifier) {
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

    Column(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
    ) {
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
                modifier = Modifier.height(128.dp),
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
    }
}

