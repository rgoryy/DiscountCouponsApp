package com.grigorii.couponsapp.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grigorii.couponsapp.R

@Composable
fun CouponDetailsScreen(modifier: Modifier = Modifier) {
    var couponDetails = CardItemContent(
        title = "Консультации психолога",
        painter = painterResource(id = R.drawable.psycholog),
        imageDescription = "Консультации психолога",
        location = "г. Томск",
        price = "2000 руб.",
        validityPeriod = "sss"
    )

    LazyColumn(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                HeaderSection(title = "Купон")

                OutlinedCard(
                    modifier = Modifier.padding(top = 32.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFBFE),
                    )
                ) {
                    Image(
                        painter = couponDetails.painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(237.dp),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                }

                Text(
                    "Консультации психолога",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                AddToFavoritesButton(modifier = Modifier.padding(top = 16.dp))

                DescriptionSection(title = couponDetails.title, description = "Получите скидочный купон на консультацию у профессионального психолога! Погрузитесь в мир самопознания и улучшите свое психическое здоровье по специальной цене. Не упустите возможность сделать шаг к лучшей версии себя!\u2028Получите скидочный купон на консультацию у профессионального психолога! Получите скидочный купон на консультацию у профессионального психолога! Погрузитесь в мир самопознания и улучшите свое психическое здоровье по специальной цене. Не упустите возможность сделать шаг к лучшей версии себя!\u2028Получите скидочный купон на консультацию у профессионального психолога!")

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 71.dp),
                    onClick = { }
                ) {
                    Text("Купить купон")
                }
            }
        }
    }
}

@Composable
fun AddToFavoritesButton(modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { },
        modifier = modifier.height(40.dp)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "favorite",
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "В избранное",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun DescriptionSection(modifier: Modifier = Modifier, title: String, description: String) {
    LazyColumn(modifier = Modifier.padding(top = 32.dp).height(140.dp)) {
        item {
            Text(
                title,
                modifier = Modifier.padding(top = 16.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Text(
                description,
                modifier = Modifier.padding(top = 12.dp),
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }
    }
}