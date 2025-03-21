package com.grigorii.couponsapp.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.grigorii.couponsapp.compose.model.MainScreenContentData
import com.grigorii.couponsapp.compose.viewmodel.MainScreenCouponLoadingState
import com.grigorii.couponsapp.compose.viewmodel.MainScreenViewModel


data class CardItemContent(
    val title: String,
    val painter: Painter,
    val imageDescription: String,
    val location: String,
    val price: String,
    val validityPeriod: String,
)

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainScreenViewModel
) {
    val offerCouponsState = viewModel.offerCouponsState
    val userCouponsState = viewModel.usersCouponsState

    if (offerCouponsState is MainScreenCouponLoadingState.Loading && userCouponsState is MainScreenCouponLoadingState.Loading) {
        MainScreenSuccess(
            navController,
            MainScreenContentData(
                null, null
            )
        )
    }

    if (offerCouponsState is MainScreenCouponLoadingState.Success || userCouponsState is MainScreenCouponLoadingState.Success) {
        MainScreenSuccess(
            navController,
            MainScreenContentData(
                offerCoupons = (offerCouponsState as? MainScreenCouponLoadingState.Success)?.coupons
                    ?: emptyList(),
                userCoupons = (userCouponsState as? MainScreenCouponLoadingState.Success)?.coupons
                    ?: emptyList()
            )
        )
    }

    if (offerCouponsState is MainScreenCouponLoadingState.Error) {
        Text(text = "Ошибка: ${offerCouponsState.message}")
        return
    }

    if (userCouponsState is MainScreenCouponLoadingState.Error) {
        Text(text = "Ошибка: ${userCouponsState.message}")
        return
    }

}

@Composable
fun MainScreenSuccess(
    navController: NavController,
    contentData: MainScreenContentData
) {
    val offerCoupons = contentData.offerCoupons
    val userCoupons = contentData.userCoupons

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
            if (contentData.offerCoupons?.isNotEmpty() == true) {
                val offerCouponsContent = offerCoupons?.map { coupon ->
                    CardItemContent(
                        title = coupon.title,
                        painter = painterResource(id = coupon.imageResourceId),
                        imageDescription = coupon.imageDescription,
                        location = coupon.location,
                        price = coupon.price,
                        validityPeriod = coupon.validityPeriod
                    )
                }
                OfferCouponsSection(offerCouponsContent, navController = navController)
            } else {
                OfferCouponsSection(null, navController = navController)
            }

        }


        item {
            if (contentData.userCoupons?.isNotEmpty() == true) {
                val userCouponsContent = userCoupons?.map { coupon ->
                    CardItemContent(
                        title = coupon.title,
                        painter = painterResource(id = coupon.imageResourceId),
                        imageDescription = coupon.imageDescription,
                        location = coupon.location,
                        price = coupon.price,
                        validityPeriod = coupon.validityPeriod
                    )
                }

                UserCouponsSection(userCouponsContent, false)
            } else {
                UserCouponsSection(null, true)
            }
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
private fun OfferCouponsSection(cardItems: List<CardItemContent>?, navController: NavController) {
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
            val cardOffersItemModifier = Modifier
                .height(IntrinsicSize.Max)
                .width(282.dp)
                .height(337.dp)

            if (cardItems == null) {
                items(3) {
                    LoadingOfferCardItem(
                        modifier = cardOffersItemModifier
                    )
                }
            } else {
                items(cardItems) { item ->
                    OfferCardItem(
                        modifier = cardOffersItemModifier,
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
}

@Composable
private fun UserCouponsSection(cardItems: List<CardItemContent>?, isLoading: Boolean) {
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

        if (isLoading) {
            repeat(3) {
                LoadingUsersCardItem()
            }
        } else {
            cardItems?.forEach { item ->
                UsersCardItem(
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
}

@Composable
fun CardItemBase(
    modifier: Modifier = Modifier,
    title: String = "...",
    location: String = "...",
    price: String = "...",
    painter: Painter? = null,
    imageDescription: String = "...",
    isLoading: Boolean = true,
    navController: NavController? = null
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFBFE),
        ),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(153.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            } else {
                painter?.let {
                    Image(
                        painter = it,
                        contentDescription = imageDescription,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(153.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
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
                    navController?.navigate("CouponDetailsScreen")
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
fun OfferCardItem(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String,
    painter: Painter,
    imageDescription: String,
    location: String,
    price: String
) {
    CardItemBase(
        modifier = modifier,
        title = title,
        location = location,
        price = price,
        painter = painter,
        imageDescription = imageDescription,
        isLoading = false,
        navController = navController
    )
}

@Composable
fun LoadingOfferCardItem(modifier: Modifier = Modifier) {
    CardItemBase(modifier = modifier)
}

@Composable
fun UsersCardItem(
    modifier: Modifier = Modifier,
    title: String = "...",
    painter: Painter? = null,
    imageDescription: String = "...",
    validityPeriod: String = "...",
    price: String = "..."
) {
    UsersCardItemBase(
        modifier = modifier,
        title = title,
        painter = painter,
        imageDescription = imageDescription,
        validityPeriod = validityPeriod,
        price = price,
        isLoading = false
    )
}

@Composable
fun LoadingUsersCardItem() {
    UsersCardItemBase()
}

@Composable
fun UsersCardItemBase(
    modifier: Modifier = Modifier,
    title: String = "...",
    painter: Painter? = null,
    imageDescription: String = "...",
    validityPeriod: String = "...",
    price: String = "...",
    isLoading: Boolean = true
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFBFE),
        ),
        modifier = modifier
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
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(30.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                } else {
                    painter?.let {
                        Image(
                            painter = it,
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
    }
}

