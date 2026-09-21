@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mycity.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycity.R
import com.example.mycity.model.Place
import com.example.mycity.model.PlaceCategory

private data class CategoryStyle(
    val emoji: String,
    val labelRes: Int,
    val colors: List<Color>
)

private fun PlaceCategory.style(): CategoryStyle = when (this) {
    PlaceCategory.CAFES -> CategoryStyle(
        "☕", R.string.category_cafes, listOf(Color(0xFF8D6E63), Color(0xFF4E342E))
    )
    PlaceCategory.RESTAURANTS -> CategoryStyle(
        "🍽️", R.string.category_restaurants, listOf(Color(0xFFFF8A65), Color(0xFFD84315))
    )
    PlaceCategory.PARKS -> CategoryStyle(
        "🌳", R.string.category_parks, listOf(Color(0xFF66BB6A), Color(0xFF2E7D32))
    )
    PlaceCategory.MUSEUMS -> CategoryStyle(
        "🏛️", R.string.category_museums, listOf(Color(0xFF9575CD), Color(0xFF4527A0))
    )
}

@Composable
fun MyCityApp(viewModel: MyCityViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val category = uiState.currentCategory
    val place = uiState.currentPlace
    val canGoBack = category != null

    val goBack: () -> Unit = {
        if (place != null) viewModel.onBackFromDetail() else viewModel.onBackFromList()
    }

    BackHandler(enabled = canGoBack) { goBack() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when {
                            place != null -> stringResource(place.nameResourceId)
                            category != null -> stringResource(category.style().labelRes)
                            else -> stringResource(R.string.app_name)
                        },
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    if (canGoBack) {
                        TextButton(onClick = goBack) {
                            Text(stringResource(R.string.back))
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                place != null -> PlaceDetailScreen(place)
                category != null -> PlaceListScreen(
                    places = uiState.placesInCategory,
                    onPlaceClick = viewModel::onPlaceSelected
                )
                else -> CategoryListScreen(onCategoryClick = viewModel::onCategorySelected)
            }
        }
    }
}

// ---------- Lista de categorías (grande) ----------

@Composable
fun CategoryListScreen(onCategoryClick: (PlaceCategory) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(PlaceCategory.values().toList()) { category ->
            CategoryCard(category = category, onClick = { onCategoryClick(category) })
        }
    }
}

@Composable
private fun CategoryCard(category: PlaceCategory, onClick: () -> Unit) {
    val style = category.style()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Brush.horizontalGradient(style.colors))
            .clickable(onClick = onClick)
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = style.emoji, fontSize = 56.sp)
        Spacer(Modifier.width(24.dp))
        Text(
            text = stringResource(style.labelRes),
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

// ---------- Lista de lugares (grande) ----------

@Composable
fun PlaceListScreen(places: List<Place>, onPlaceClick: (Place) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(places) { place ->
            PlaceCard(place = place, onClick = { onPlaceClick(place) })
        }
    }
}

@Composable
private fun PlaceCard(place: Place, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(place.imageResourceId),
                contentDescription = stringResource(place.nameResourceId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(200.dp)
                    .height(170.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(20.dp)
            ) {
                Text(
                    text = stringResource(place.nameResourceId),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "📍 " + stringResource(place.addressResourceId),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(place.descriptionResourceId),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// ---------- Detalle ----------

@Composable
fun PlaceDetailScreen(place: Place) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                Image(
                    painter = painterResource(place.imageResourceId),
                    contentDescription = stringResource(place.nameResourceId),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xCC000000))
                            )
                        )
                )
                Text(
                    text = stringResource(place.nameResourceId),
                    color = Color.White,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(24.dp)
                )
            }
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "📍 " + stringResource(place.addressResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(place.descriptionResourceId),
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = 28.sp
                )
            }
        }
    }
}