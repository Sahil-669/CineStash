package org.example.cinestash.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.cinestash.ui.navigation.Detail
import org.example.cinestash.ui.navigation.Home
import org.example.cinestash.ui.navigation.Profile
import org.example.cinestash.ui.navigation.Search

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Home>() } == true,
                    onClick = {
                        navController.navigate(Home) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = {Icon(Icons.Default.Search, contentDescription = "Search")},
                    label = {Text("Search")},
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Search>() } == true,
                    onClick = {
                        navController.navigate(Search) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = {Icon(Icons.Default.AccountCircle, contentDescription = "Profile")},
                    label = {Text("Profile")},
                    selected = currentDestination?.hierarchy?.any { it.hasRoute<Profile>() } == true,
                    onClick = {
                        navController.navigate(Profile) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<Home> {
                HomeScreen(
                    onMovieClick = { movieId ->
                        navController.navigate(Detail(movieId))
                    }
                )
            }
            composable<Search> {
                SearchScreen(
                    onMovieClick = { movieId ->
                        navController.navigate(Detail(movieId))
                    }
                )
            }
            composable<Profile> {
                StashScreen (
                    onMovieClick = {movieId ->
                        navController.navigate(Detail(movieId))
                    }
                )
            }
            composable<Detail> { backStackEntry ->
                val detail: Detail = backStackEntry.toRoute()
                DetailScreen(
                    movieId = detail.movieId,
                    onBackClick = {navController.popBackStack()}
                )
            }
        }
    }
}