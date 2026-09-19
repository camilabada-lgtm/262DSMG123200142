package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

data class Artwork(
    val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val artworks = listOf(
        Artwork(
            R.drawable.starry_night_canvas_vincent_van_gogh_new_1889,
            "La noche estrellada",
            "Vincent van Gogh",
            "1889"
        ),
        Artwork(
            R.drawable.the_mona_lisa_painting_by_leonardo_da_vinci_the_louvre_museum_paris_bk5jgd,
            "La Mona Lisa",
            "Leonardo da Vinci",
            "1503"
        ),
        Artwork(
            R.drawable.the_starry_night_vincent_van_gogh_2pjygtr,
            "La noche estrellada (version 2)",
            "Vincent van Gogh",
            "1889"
        )
    )

    var currentIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Muro con la obra de arte
        Image(
            painter = painterResource(id = currentArtwork.imageRes),
            contentDescription = currentArtwork.title,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Descriptor de la obra
        Text(
            text = currentArtwork.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${currentArtwork.artist} (${currentArtwork.year})",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // Controlador de pantalla
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(onClick = {
                currentIndex = when (currentIndex) {
                    0 -> artworks.size - 1
                    else -> currentIndex - 1
                }
            }) {
                Text("Anterior")
            }
            Button(onClick = {
                currentIndex = when (currentIndex) {
                    artworks.size - 1 -> 0
                    else -> currentIndex + 1
                }
            }) {
                Text("Siguiente")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}