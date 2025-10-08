package com.paxtech.mobileapp.features.clientDashboard.presentation.timeselection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class TimeSlot(
    val time: String,
    val isAvailable: Boolean = true
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSelectionScreen(
    serviceName: String,
    servicePrice: String,
    serviceDuration: Int,
    selectedProfessional: String,
    salonName: String = "Glow & Go Hair Studio",
    salonAddress: String = "Av. Primavera 123, Santiago de Surco, Lima – Perú",
    onBack: () -> Unit,
    onContinue: (selectedDate: String, selectedTime: String, formattedDate: String, formattedTime: String) -> Unit
) {
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var selectedTime by remember { mutableStateOf<String?>(null) }

    val october2025Dates = listOf(
        "6 Lun", "7 Mar", "8 Mié", "9 Jue", "10 Vie", "11 Sáb", "12 Dom"
    )

    val timeSlots = listOf(
        TimeSlot("10:00 am"),
        TimeSlot("10:30 am"),
        TimeSlot("11:00 am"),
        TimeSlot("11:30 am"),
        TimeSlot("12:00 pm"),
        TimeSlot("12:30 pm"),
        TimeSlot("13:00 pm"),
        TimeSlot("13:30 pm"),
        TimeSlot("14:00 pm"),
        TimeSlot("14:30 pm"),
        TimeSlot("15:00 pm")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seleccionar hora") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            servicePrice,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "$serviceName - $serviceDuration min",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        selectedProfessional,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        if (selectedDate != null && selectedTime != null) {
                            "${selectedDate!!.substring(0, 2)} de octubre - ${selectedTime}"
                        } else {
                            "Selecciona fecha y hora"
                        },
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (selectedDate != null && selectedTime != null) {
                                val formattedDate = "Martes ${selectedDate!!.substring(0, 2)} de octubre"
                                val formattedTime = "${selectedTime!!} – ${calculateEndTime(selectedTime!!, serviceDuration)}"
                                onContinue(selectedDate!!, selectedTime!!, formattedDate, formattedTime)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = selectedDate != null && selectedTime != null
                    ) {
                        Text("Continuar")
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Cualquier profesional",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Divider()
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Octubre 2025",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        october2025Dates.forEach { date ->
                            DateChip(
                                date = date,
                                isSelected = selectedDate == date,
                                onSelect = { selectedDate = date }
                            )
                        }
                    }
                }
                Divider()
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Horarios disponibles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }

            items(timeSlots) { timeSlot ->
                TimeSlotItem(
                    timeSlot = timeSlot,
                    isSelected = selectedTime == timeSlot.time,
                    onSelect = { selectedTime = timeSlot.time }
                )
                Divider()
            }

            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun DateChip(
    date: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val parts = date.split(" ")
    val dayNumber = parts[0]
    val dayName = parts[1]

    Card(
        modifier = Modifier
            .width(40.dp)
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayNumber,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = dayName,
                style = MaterialTheme.typography.labelSmall,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TimeSlotItem(
    timeSlot: TimeSlot,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                enabled = timeSlot.isAvailable,
                onClick = onSelect
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = timeSlot.time,
            style = MaterialTheme.typography.bodyLarge,
            color = if (!timeSlot.isAvailable) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            else if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        )

        if (isSelected) {
            Text(
                text = "Seleccionado",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        } else if (timeSlot.isAvailable) {
            Button(onClick = onSelect) {
                Text("Seleccionar")
            }
        } else {
            Text(
                text = "No disponible",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }
    }
}

private fun calculateEndTime(startTime: String, duration: Int): String {
    return when (startTime) {
        "10:00 am" -> "10:50 am"
        "10:30 am" -> "11:20 am"
        "11:00 am" -> "11:50 am"
        "11:30 am" -> "12:20 pm"
        "12:00 pm" -> "12:50 pm"
        "12:30 pm" -> "13:20 pm"
        "13:00 pm" -> "13:50 pm"
        "13:30 pm" -> "14:20 pm"
        "14:00 pm" -> "14:50 pm"
        "14:30 pm" -> "15:20 pm"
        "15:00 pm" -> "15:50 pm"
        else -> "${duration} min después"
    }
}