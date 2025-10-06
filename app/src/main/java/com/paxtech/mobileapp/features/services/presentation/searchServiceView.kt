package com.paxtech.mobileapp.features.services.presentation

import android.R
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paxtech.mobileapp.shared.model.Service

@Preview(showBackground = true)
@Composable
fun SearchServiceView() {

    val ahorita = listOf<String>("holi", "boli", "pink", "pie", "chi")

    val servicios = listOf<Service>(
        Service( 1, "nat", 1000, 500.00, "yo" ),
        Service( 1, "nat", 1000, 500.00, "yo" ),
        Service( 1, "nat", 1000, 500.00, "yo" )
    )

    val isSearchBarActive = remember {
        mutableStateOf<Boolean>(true)
    }

    Column(
        modifier = Modifier
        .fillMaxSize().padding(top = 16.dp)
    ){
        if (isSearchBarActive.value) {
            IconButton(onClick = {
                isSearchBarActive.value = false
            }) {
                Icon(Icons.Default.ArrowBackIosNew, contentDescription = null)
            }
        }
        OutlinedTextField(
            value = "",
            onValueChange = {
                ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            trailingIcon = {
                IconButton(onClick = {
                    isSearchBarActive.value = true
                }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
            }
        )
        Column(
            modifier = Modifier.padding(top = 32.dp)
        ) {
            if (!isSearchBarActive.value) {
                Box(modifier = Modifier
                    .padding(bottom = 12.dp)
                    .padding(horizontal = 8.dp)
                ) {
                    Text(text = "Categorias",
                        style = MaterialTheme.typography.headlineLarge)
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(ahorita) { category ->
                        CategoryCard(category)
                    }
                }
            }
            if (isSearchBarActive.value) {
                Box(modifier = Modifier
                    .padding(bottom = 12.dp)
                    .padding(horizontal = 8.dp)
                ) {
                    Text(text = "Resultados",
                        style = MaterialTheme.typography.headlineLarge)
                }
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(servicios) { service ->
                        ServiceCard(service)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryCard(title: String) {
    Card (
        modifier = Modifier
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimaryFixedVariant, shape = CardDefaults.elevatedShape)
            .padding(8.dp)
            .height(70.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,

        ){
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryFixedVariant,
                    fontFamily = FontFamily.Cursive
                )
            )
        }
    }
}

@Composable
fun ServiceCard(service: Service) {
    Card (
        modifier = Modifier
            .border(width = 2.dp, color = MaterialTheme.colorScheme.onPrimaryFixedVariant, shape = CardDefaults.elevatedShape)
            .padding(8.dp)
            .height(150.dp)
    ){
        Box(
            modifier = Modifier.padding(4.dp)
        ){
            Text(
                text = service.name,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Box(
            modifier = Modifier.padding(4.dp)
        ){
            Text(
                text = "Aprox duration: " + service.duration + "minutes",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Row {
            Box(
                modifier = Modifier.padding(4.dp)
            ){
                Text(
                    text = "Salon: " + service.provider,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Box(
                modifier = Modifier.padding(4.dp)
            ){
                Text(
                    text = "Price S/: " + service.price,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        ElevatedButton(onClick = {}) {
            Text("Reservar ahora")
        }
    }
}