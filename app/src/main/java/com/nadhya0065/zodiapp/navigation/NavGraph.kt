package com.nadhya0065.zodiapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nadhya0065.zodiapp.AboutScreen
import com.nadhya0065.zodiapp.HomeScreen
import com.nadhya0065.zodiapp.ResultScreen
import com.nadhya0065.zodiapp.ZodiacScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Zodiac.route) {
            ZodiacScreen(navController)
        }
        composable(Screen.Result.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val gender = backStackEntry.arguments?.getString("gender") ?: ""
            val day = backStackEntry.arguments?.getString("day") ?: ""
            val month = backStackEntry.arguments?.getString("month") ?: ""
            val year = backStackEntry.arguments?.getString("year") ?: ""

            ResultScreen(
                name = name,
                gender = gender,
                day = day,
                month = month,
                year = year,
                navController = navController
            )
        }


        composable(Screen.About.route) {
            AboutScreen(navController)
        }
    }
}
