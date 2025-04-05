package com.nadhya0065.zodiapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadhya0065.zodiapp.navigation.Screen
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ZodiacScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf<String?>(null) }
    var dayError by remember { mutableStateOf<String?>(null) }
    var yearError by remember { mutableStateOf<String?>(null) }
    var generalError by remember { mutableStateOf("") }

    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    val maleLabel = stringResource(R.string.male)
    val femaleLabel = stringResource(R.string.female)
    val nameLabel = stringResource(R.string.name_placeholder)
    val dayLabel = stringResource(R.string.day)
    val monthLabel = stringResource(R.string.month)
    val yearLabel = stringResource(R.string.year)
    val seeZodiac = stringResource(R.string.see_zodiac)
    val invalidInput = stringResource(R.string.invalid_input)
    val invalidYear = stringResource(R.string.invalid_year)

    Scaffold(
        topBar = { ZodiacTopBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = if (name.matches(Regex("^[a-zA-Z\\s]+\$"))) null
                        else "Nama hanya boleh huruf dan spasi."
                    },
                    label = { Text(nameLabel) },
                    modifier = Modifier.fillMaxWidth(),
                    isError = nameError != null
                )
                if (nameError != null) {
                    Text(
                        text = nameError ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = gender == maleLabel,
                        onClick = { gender = maleLabel }
                    )
                    Text(maleLabel)

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = gender == femaleLabel,
                        onClick = { gender = femaleLabel }
                    )
                    Text(femaleLabel)
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = day,
                    onValueChange = {
                        day = it
                        val dayInt = it.toIntOrNull()
                        dayError = if (dayInt != null && dayInt in 1..31) null
                        else "Tanggal harus angka antara 1 hingga 31."
                    },
                    label = { Text(dayLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    isError = dayError != null
                )
                if (dayError != null) {
                    Text(
                        text = dayError ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                OutlinedTextField(
                    value = month,
                    onValueChange = { month = it },
                    label = { Text(monthLabel) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = year,
                    onValueChange = {
                        year = it
                        val yearInt = it.toIntOrNull()
                        yearError = if (yearInt != null && yearInt <= currentYear) null else invalidYear

                    },
                    label = { Text(yearLabel) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    isError = yearError != null
                )
                if (yearError != null) {
                    Text(
                        text = yearError ?: "",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                if (generalError.isNotEmpty()) {
                    Text(
                        text = generalError,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    generalError = ""

                    val dayInt = day.toIntOrNull()
                    val yearInt = year.toIntOrNull()

                    val validName = name.matches(Regex("^[a-zA-Z\\s]+\$"))
                    val validDay = dayInt != null && dayInt in 1..31
                    val validYear = yearInt != null && yearInt <= currentYear

                    nameError = if (!validName) "Nama hanya boleh huruf dan spasi." else null
                    dayError = if (!validDay) "Tanggal harus antara 1 hingga 31." else null
                    yearError = if (!validYear) "Tahun tidak boleh lebih dari $currentYear." else null

                    if (name.isEmpty() || gender.isEmpty() || day.isEmpty() || month.isEmpty() || year.isEmpty()) {
                        generalError = invalidInput
                    } else if (nameError == null && dayError == null && yearError == null) {
                        navController.navigate(
                            Screen.Result.passData(
                                name = name,
                                gender = gender,
                                day = day,
                                month = month,
                                year = year
                            )
                        )
                    } else {
                        generalError = "Periksa kembali inputan Anda."
                    }
                }) {
                    Text(seeZodiac)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
