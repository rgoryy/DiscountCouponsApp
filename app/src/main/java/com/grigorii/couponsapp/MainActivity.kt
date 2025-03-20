package com.grigorii.couponsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grigorii.couponsapp.compose.CouponsTheme
import com.grigorii.couponsapp.compose.screens.CatalogScreen
import com.grigorii.couponsapp.compose.screens.CouponDetailsScreen
import com.grigorii.couponsapp.compose.screens.FavoritesScreen
import com.grigorii.couponsapp.compose.screens.FilterScreen
import com.grigorii.couponsapp.compose.screens.GreetingsScreen
import com.grigorii.couponsapp.compose.screens.MainScreen
import com.grigorii.couponsapp.compose.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {

    private val mainScreenViewModel = MainScreenViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CouponsTheme {
                val navController = rememberNavController()

                val showBottomBarState = rememberSaveable { mutableStateOf(true) }

                val navItemList = listOf(
                    NavItem("Главная", Icons.Default.Home),
                    NavItem("Каталог", Icons.Default.Menu),
                    NavItem("Избранное", Icons.Default.FavoriteBorder),
                )

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                DisposableEffect(navBackStackEntry) {
                    val currentRoute = navBackStackEntry?.destination?.route
                    showBottomBarState.value = currentRoute != "GreetingsScreen"

                    onDispose { }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedVisibility(
                            visible = showBottomBarState.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                            content = {
                                NavigationBar {
                                    val currentDestination = navBackStackEntry?.destination
                                    navItemList.forEach { screen ->
                                        NavigationBarItem(
                                            selected = currentDestination?.hierarchy?.any {
                                                it.route == screen.label
                                            } == true,
                                            onClick = { navController.navigate(screen.label) },
                                            icon = {
                                                Icon(
                                                    imageVector = screen.icon,
                                                    contentDescription = "Icon"
                                                )
                                            },
                                            label = { Text(text = screen.label) }
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "Главная",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("Главная") {
                            mainScreenViewModel.fetchContent()
                            MainScreen(
                                Modifier.padding(innerPadding),
                                navController = navController,
                                viewModel = mainScreenViewModel
                            )
                            showBottomBarState.value = true
                        }
                        composable("Каталог") {
                            CatalogScreen(
                                Modifier.padding(innerPadding),
                                navController = navController
                            )
                            showBottomBarState.value = true
                        }
                        composable("Избранное") {
                            FavoritesScreen(Modifier.padding(innerPadding))
                            showBottomBarState.value = true
                        }
                        composable("GreetingsScreen") {
                            showBottomBarState.value = false
                            GreetingsScreen(
                                mainNavController = navController,
                                stateToNavigate = "Главная"
                            )
                        }
                        composable("FilterScreen") {
                            FilterScreen(
                                navController = navController
                            )
                        }
                        composable("CouponDetailsScreen") { CouponDetailsScreen() }
                    }
                }
            }
        }
    }
}