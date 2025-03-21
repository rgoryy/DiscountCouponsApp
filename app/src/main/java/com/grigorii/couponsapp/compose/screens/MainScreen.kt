package com.grigorii.couponsapp.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grigorii.couponsapp.R


data class CardItemContent(
    val title: String,
    val painter: Painter,
    val imageDescription: String,
    val location: String,
    val price: String,
    val validityPeriod: String,
)

@Composable
fun MainScreen(modifier: Modifier = Modifier, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            HeaderSection()
        }

        item {
            val tempItemss = listOf(
                CardItemContent(
                    title = "Консультации психолога",
                    painter = painterResource(id = R.drawable.psycholog),
                    imageDescription = "Консультации психолога",
                    location = "г. Томск",
                    price = "2000 руб.",
                    validityPeriod = "sss"
                ),
                CardItemContent(
                    title = "Занятия по танцам",
                    painter = painterResource(id = R.drawable.sportclub),
                    imageDescription = "Занятия по танцам",
                    location = "г.Томск",
                    price = "2000 руб.",
                    validityPeriod = "sss"
                ),
            )

            CouponsSection(tempItemss, navController = navController)
        }


        item {
            val tempItemss = listOf(
                CardItemContent(
                    title = "Курс “Разработка Android-приложений”",
                    painter = painterResource(id = R.drawable.android),
                    imageDescription = "Консультации психолога",
                    location = "г. Томск",
                    price = "2000 руб.",
                    validityPeriod = "действителен до 31.05.2025"
                ),
                CardItemContent(
                    title = "Курс по английскому языку",
                    painter = painterResource(id = R.drawable.engl),
                    imageDescription = "Курс по английскому языку",
                    location = "г.Томск",
                    price = "2000 руб.",
                    validityPeriod = "действителен до 20.05.2025"
                ),
                CardItemContent(
                    title = "Онлайн-курс по фотографии",
                    painter = painterResource(id = R.drawable.photo),
                    imageDescription = "Онлайн-курс по фотографии",
                    location = "г.Томск",
                    price = "2000 руб.",
                    validityPeriod = "действителен до 20.05.2025"
                ),
            )

            OffersSection(tempItemss)
        }
    }
}

@Composable
private fun HeaderSection() {
    Row(
        modifier = Modifier
            .background(Color(0xFFFFFBFE))
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { },
            modifier = Modifier.size(40.dp),
            shape = CircleShape
        ) {
            Text(
                "A",
                style = TextStyle(color = Color(0xFF21005D)),
                fontSize = 16.sp
            )
        }

        Spacer(Modifier.width(16.dp))

        Text(
            "Привет, Григорий",
            style = TextStyle(
                color = Color(0xFF1C1B1F),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
            )
        )
    }

    HorizontalDivider(thickness = 1.dp, color = Color(0xFFCAC4D0))
}

@Composable
private fun CouponsSection(tempItemss: List<CardItemContent>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Предложения",
            style = TextStyle(
                color = Color(0xFF1C1B1F),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
            )
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(tempItemss) { item ->
                CardItem(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .width(282.dp)
                        .height(337.dp),
                    navController = navController,
                    title = item.title,
                    painter = item.painter,
                    imageDescription = item.imageDescription,
                    location = item.location,
                    price = item.price
                )
            }
        }
    }
}

@Composable
private fun OffersSection(tempItemss: List<CardItemContent>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 32.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            "Мои купоны",
            style = TextStyle(
                color = Color(0xFF1C1B1F),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        tempItemss.forEach { item ->
            CardOfferItem(
                modifier = Modifier.height(IntrinsicSize.Max),
                title = item.title,
                painter = item.painter,
                imageDescription = item.imageDescription,
                price = item.price,
                validityPeriod = item.validityPeriod
            )
        }
    }
}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    painter: Painter,
    imageDescription: String,
    location: String,
    price: String
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFBFE),
        ),
        modifier = modifier
    ) {

        Column() {
            Image(
                painter = painter,
                contentDescription = imageDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(153.dp),
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
            Text(
                text = title,
                style = TextStyle(
                    color = Color(0xFF1C1B1F),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = location,
                style = TextStyle(
                    color = Color(0xFF1C1B1F),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
        }

        Column(modifier = Modifier.padding(top = 24.dp, start = 16.dp)) {
            Text(
                price,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        Row(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = {
                    navController.navigate("CouponDetailsScreen")
                },
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Подробнее")
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .height(40.dp)
                    .width(42.dp)
                    .align(Alignment.CenterVertically),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "favorite",
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}


@Composable
fun CardOfferItem(
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter,
    imageDescription: String,
    validityPeriod: String,
    price: String
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFBFE),
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color(0xFF1C1B1F),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = validityPeriod,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(
                        fontSize = 14.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

