package com.grigorii.couponsapp.compose.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grigorii.couponsapp.R
import com.grigorii.couponsapp.compose.model.Coupon
import com.grigorii.couponsapp.compose.viewmodel.CatalogScreenViewModel
import com.grigorii.couponsapp.compose.viewmodel.CouponLoadingState

@Composable
fun CatalogScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CatalogScreenViewModel
) {

    val offerCouponsState = viewModel.offerCouponsState

    val offerCoupons =
        (offerCouponsState as? CouponLoadingState.Success)?.coupons ?: emptyList()

    when (offerCouponsState) {
        is CouponLoadingState.Error -> {
            Text(text = "Ошибка: ${offerCouponsState.message}")
            return
        }

        is CouponLoadingState.Loading -> LoadingScreen()

        is CouponLoadingState.Success -> CatalogScreenSuccess(
            navController = navController,
            offerCoupons = offerCoupons,
            onLoadMoreOfferCouponsButtonClick = {
                viewModel.fetchOfferCoupons()
            }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreenSuccess(
    modifier: Modifier = Modifier,
    navController: NavController,
    offerCoupons: List<Coupon>,
    onLoadMoreOfferCouponsButtonClick: () -> Unit,
) {
    var text by remember { mutableStateOf("") }

    var active by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {

            HeaderSection()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputField = {
                            SearchBarDefaults.InputField(
                                query = text,
                                onQueryChange = {
                                    text = it
                                },
                                onSearch = {
                                    active = false
                                },
                                expanded = active,
                                onExpandedChange = { active = it },
                                enabled = true,
                                placeholder = {
                                    Text(stringResource(id = R.string.search_bar_placeholder))
                                },
                                leadingIcon = null,
                                trailingIcon = {
                                    Icon(
                                        Icons.Default.Search,
                                        contentDescription = "favorite",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                interactionSource = null,
                            )
                        },
                        expanded = active,
                        onExpandedChange = {
                            active = it
                        },
                        shape = SearchBarDefaults.inputFieldShape,
                        tonalElevation = SearchBarDefaults.TonalElevation,
                        shadowElevation = SearchBarDefaults.ShadowElevation,
                        windowInsets = SearchBarDefaults.windowInsets,
                        content = { }
                    )
                }

                item {
                    FilterButton(
                        modifier = Modifier.padding(top = 32.dp),
                        navController = navController,
                        stateToNavigate = "FilterScreen"
                    )
                }

                item {
                    val offerItemsContent = offerCoupons.map { coupon ->
                        CardItemContent(
                            title = coupon.title,
                            painter = painterResource(id = coupon.imageResourceId),
                            imageDescription = coupon.imageDescription,
                            location = coupon.location,
                            price = coupon.price,
                            validityPeriod = coupon.validityPeriod,
                            id = coupon.id,
                            description = coupon.description
                        )
                    }


                    CatalogCouponsSection(
                        cardItems = offerItemsContent,
                        navController = navController
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 47.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ShowMoreButton(onButtonClick = {
                            onLoadMoreOfferCouponsButtonClick()
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun HeaderSection() {
    Row(
        modifier = Modifier
            .background(Color(0xFFFFFBFE))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(id = R.string.catalog),
            style = TextStyle(
                color = Color(0xFF1C1B1F),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp
            ),
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, top = 8.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun FilterButton(
    modifier: Modifier = Modifier, navController: NavController, stateToNavigate: String
) {
    OutlinedButton(
        onClick = {
            navController.navigate(stateToNavigate)
        },
        modifier = modifier.height(40.dp)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "favorite",
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.filter_button_name),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun ShowMoreButton(modifier: Modifier = Modifier, onButtonClick: () -> Unit = { }) {
    OutlinedButton(
        onClick = onButtonClick,
        modifier = modifier.height(40.dp)
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "show more",
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = stringResource(id = R.string.show_more_button_name),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}


@Composable
fun CatalogCouponsSection(cardItems: List<CardItemContent>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        cardItems.forEach { item ->
            OfferCardItem(
                modifier = Modifier.height(337.dp),
                navController = navController,
                title = item.title,
                painter = item.painter,
                imageDescription = item.imageDescription,
                price = item.price,
                location = item.location,
                id = item.id
            )
        }
    }
}