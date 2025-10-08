package com.paxtech.mobileapp.features.clientDashboard.presentation.salondetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.paxtech.mobileapp.features.clientDashboard.presentation.details.AboutUi
import com.paxtech.mobileapp.features.clientDashboard.presentation.details.ReviewUi
import com.paxtech.mobileapp.features.clientDashboard.presentation.details.ServiceUi
import com.paxtech.mobileapp.shared.model.Salon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalonDetailScreen(
    salon: Salon?,
    services: List<ServiceUi>,
    reviews: List<ReviewUi>,
    about: AboutUi,
    onBack: () -> Unit,
    onReserveService: (ServiceUi) -> Unit
) {
    var tab by remember { mutableStateOf(0) }
    val tabs = listOf("Servicios", "Reseñas", "Acerca de")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(salon?.companyName ?: "Salón") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: favorite */ }) {
                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Favorite")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                AsyncImage(
                    model = salon?.coverImageUrl ?: "",
                    contentDescription = salon?.companyName ?: "Salón",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(8.dp))

                Column(Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        salon?.companyName ?: "Nombre no disponible",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Place, contentDescription = null)
                        Spacer(Modifier.width(6.dp))
                        Text(about.address)
                    }
                    Spacer(Modifier.height(8.dp))

                    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AssistChip(onClick = {}, label = { Text("Instagram") })
                        AssistChip(onClick = {}, label = { Text("TikTok") })
                    }
                }

                Spacer(Modifier.height(12.dp))

                TabRow(selectedTabIndex = tab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = tab == index,
                            onClick = { tab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }

            when (tab) {
                0 -> {
                    items(services) { svc ->
                        ServiceRow(
                            svc = svc,
                            onReserveClick = { onReserveService(svc) }
                        )
                        Divider()
                    }
                    item { Spacer(Modifier.height(16.dp)) }
                }
                1 -> {
                    items(reviews) { rev ->
                        ReviewRow(rev)
                        Divider()
                    }
                    item { Spacer(Modifier.height(16.dp)) }
                }
                2 -> {
                    item {
                        AboutBlock(about)
                        Spacer(Modifier.height(24.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ServiceRow(
    svc: ServiceUi,
    onReserveClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(svc.title, fontWeight = FontWeight.SemiBold)
            Text(svc.subtitle, style = MaterialTheme.typography.bodySmall)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(svc.price, fontWeight = FontWeight.SemiBold)
            Text("${svc.durationMins} mins", style = MaterialTheme.typography.labelSmall)
            Spacer(Modifier.height(6.dp))
            AssistChip(
                onClick = onReserveClick,
                label = { Text("Reservar") }
            )
        }
    }
}

@Composable
private fun ReviewRow(rev: ReviewUi) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(rev.author, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
            Row {
                repeat(rev.rating) { Icon(Icons.Default.Star, contentDescription = null) }
            }
        }
        Spacer(Modifier.height(6.dp))
        Text(rev.comment)
    }
}

@Composable
private fun AboutBlock(about: AboutUi) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Text("Sobre nosotros:", style = MaterialTheme.typography.titleMedium)
        Text(about.description, modifier = Modifier.padding(top = 4.dp))

        Spacer(Modifier.height(12.dp))
        Text("Horario", style = MaterialTheme.typography.titleMedium)
        about.schedule.forEach { Text("• $it") }

        Spacer(Modifier.height(12.dp))
        Text("Ubicación", style = MaterialTheme.typography.titleMedium)
        Text(about.address)

        Spacer(Modifier.height(12.dp))
        Text("Teléfono", style = MaterialTheme.typography.titleMedium)
        Text(about.phone)
    }
}