package com.nadhya0065.zodiapp

data class Zodiac(val name: String, val description: String, val icon: Int)

object ZodiacData {

    private val maxDaysPerMonth = mapOf(
        "januari" to 31,
        "februari" to 29,
        "maret" to 31,
        "april" to 30,
        "mei" to 31,
        "juni" to 30,
        "juli" to 31,
        "agustus" to 31,
        "september" to 30,
        "oktober" to 31,
        "november" to 30,
        "desember" to 31
    )

    fun getZodiac(day: Int, month: String): Zodiac {
        val monthLower = month.lowercase()

        val maxDay = maxDaysPerMonth[monthLower]
        if (maxDay == null || day !in 1..maxDay) {
            return Zodiac(
                "Tidak diketahui",
                "Tanggal atau bulan tidak valid.",
                R.drawable.ic_launcher_foreground
            )
        }

        return when (monthLower) {
            "maret" -> if (day >= 21) Zodiac("Aries", "Berani, penuh semangat.", R.drawable.aries)
            else Zodiac("Pisces", "Empati, kreatif, dan intuitif.", R.drawable.pisces)

            "april" -> if (day <= 19) Zodiac("Aries", "Berani, penuh semangat.", R.drawable.aries)
            else Zodiac("Taurus", "Stabil, setia, dan sabar.", R.drawable.taurus)

            "mei" -> if (day <= 20) Zodiac("Taurus", "Stabil, setia, dan sabar.", R.drawable.taurus)
            else Zodiac("Gemini", "Cerdas, komunikatif, dan fleksibel.", R.drawable.gemini)

            "juni" -> if (day <= 20) Zodiac("Gemini", "Cerdas, komunikatif, dan fleksibel.", R.drawable.gemini)
            else Zodiac("Cancer", "Penuh emosi, perhatian, dan penyayang.", R.drawable.cancer)

            "juli" -> if (day <= 22) Zodiac("Cancer", "Penuh emosi, perhatian, dan penyayang.", R.drawable.cancer)
            else Zodiac("Leo", "Karismatik, percaya diri, dan kuat.", R.drawable.leo)

            "agustus" -> if (day <= 22) Zodiac("Leo", "Karismatik, percaya diri, dan kuat.", R.drawable.leo)
            else Zodiac("Virgo", "Perfeksionis, cerdas, dan praktis.", R.drawable.virgo)

            "september" -> if (day <= 22) Zodiac("Virgo", "Perfeksionis, cerdas, dan praktis.", R.drawable.virgo)
            else Zodiac("Libra", "Harmonis, diplomatis, dan adil.", R.drawable.libra)

            "oktober" -> if (day <= 22) Zodiac("Libra", "Harmonis, diplomatis, dan adil.", R.drawable.libra)
            else Zodiac("Scorpio", "Misterius, kuat, dan intens.", R.drawable.scorpio)

            "november" -> if (day <= 21) Zodiac("Scorpio", "Misterius, kuat, dan intens.", R.drawable.scorpio)
            else Zodiac("Sagitarius", "Petualang, optimis, dan mandiri.", R.drawable.sagitarius)

            "desember" -> if (day <= 21) Zodiac("Sagitarius", "Petualang, optimis, dan mandiri.", R.drawable.sagitarius)
            else Zodiac("Capricorn", "Ambisius, disiplin, dan pekerja keras.", R.drawable.capricorn)

            "januari" -> if (day <= 19) Zodiac("Capricorn", "Ambisius, disiplin, dan pekerja keras.", R.drawable.capricorn)
            else Zodiac("Aquarius", "Inovatif, unik, dan mandiri.", R.drawable.aquarius)

            "februari" -> if (day <= 18) Zodiac("Aquarius", "Inovatif, unik, dan mandiri.", R.drawable.aquarius)
            else Zodiac("Pisces", "Empati, kreatif, dan intuitif.", R.drawable.pisces)

            else -> Zodiac("Tidak diketahui", "Tanggal atau bulan tidak valid.", R.drawable.ic_launcher_foreground)
        }
    }
}
