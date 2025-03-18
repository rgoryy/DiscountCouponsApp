package com.grigorii.couponsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.getValue
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
import com.grigorii.couponsapp.compose.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CouponsTheme {
                val navController = rememberNavController()

                val navItemList = listOf(
                    NavItem("Главная", Icons.Default.Home),
                    NavItem("Каталог", Icons.Default.Menu),
                    NavItem("Избранное", Icons.Default.FavoriteBorder),
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            navItemList.forEach { screen ->
                                NavigationBarItem(
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.label } == true,
                                    onClick = {
                                        navController.navigate(screen.label)
                                    },
                                    icon = {
                                        Icon(imageVector = screen.icon, contentDescription = "Icon")
                                    },
                                    label = {
                                        Text(text = screen.label)
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = "Главная",
                        Modifier.padding(innerPadding)
                    ) {
                        composable("Главная") { MainScreen(Modifier.padding(innerPadding)) }
                        composable("Каталог") { CatalogScreen(Modifier.padding(innerPadding)) }
                        composable("Избранное") { FavoritesScreen(Modifier.padding(innerPadding)) }
                    }
                }
            }
        }
    }
}