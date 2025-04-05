package com.nadhya0065.zodiapp.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Zodiac : Screen("zodiac")
    data object About : Screen("about")

    data object Result : Screen("result") {
        fun passData(name: String, gender: String, day: String, month: String, year: String): String {
            return "result?name=${name}&gender=${gender}&day=${day}&month=${month}&year=${year}"
        }
    }
}
