package com.paxtech.mobileapp.features.clientDashboard.presentation.professionalselection

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
import com.paxtech.mobileapp.features.clientDashboard.presentation.details.ServiceUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfessionalSelectionScreen(
    service: ServiceUi,
    onBack: () -> Unit,
    onContinue: (selectedProfessional: String) -> Unit
) {
    var selectedProfessional by remember { mutableStateOf<String?>(null) }
    val professionals = listOf("Cualquier profesional", "Pedro", "Jose", "Ana", "Carla")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seleccionar profesional") },
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
                            service.price,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${service.title} - ${service.durationMins} min",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        selectedProfessional ?: "Cualquier profesional",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = {
                            onContinue(selectedProfessional ?: "Cualquier profesional")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = true
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
            items(professionals) { professional ->
                ProfessionalItem(
                    name = professional,
                    isSelected = selectedProfessional == professional,
                    onSelect = { selectedProfessional = professional }
                )
                Divider()
            }
        }
    }
}

@Composable
private fun ProfessionalItem(
    name: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onSelect() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge
        )
        if (isSelected) {
            Text(
                text = "Seleccionado",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Button(onClick = onSelect) {
                Text("Seleccionar")
            }
        }
    }
}