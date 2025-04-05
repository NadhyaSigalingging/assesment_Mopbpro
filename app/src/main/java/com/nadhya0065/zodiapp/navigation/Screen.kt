package com.nadhya0065.zodiapp.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Zodiac : Screen("zodiac")
    data object About : Screen("about_screen")
    data object Result : Screen("result_screen/{name}/{gender}/{day}/{month}/{year}") {
        fun passData(name: String, gender: String, day: String, month: String, year: String): String {
            return "result_screen/$name/$gender/$day/$month/$year"
        }
    }
}
