package com.example.motivationalquotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.motivationalquotes.ui.theme.MotivationalQuotesTheme

data class Quote(
    val day: Int,
    val text: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotivationalQuotesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    QuoteList(quotes = DataSource.quotes)
                }
            }
        }
    }
}

object DataSource {
    private val phrases = listOf(
        "Cada día es una nueva oportunidad para crecer.",
        "El éxito es la suma de pequeños esfuerzos repetidos.",
        "No cuentes los días, haz que los días cuenten.",
        "La disciplina es el puente entre metas y logros.",
        "Cree en ti y todo será posible.",
        "Los grandes logros requieren tiempo y paciencia.",
        "El fracaso es parte del camino hacia el éxito.",
        "Empieza donde estás, usa lo que tienes.",
        "Tu único límite eres tú mismo.",
        "La constancia vence al talento.",
        "Cada paso cuenta, aunque sea pequeño.",
        "El cambio comienza con una decisión.",
        "No esperes el momento perfecto, crea el momento.",
        "El progreso, no la perfección, es la meta.",
        "Hoy es un buen día para empezar de nuevo.",
        "La actitud lo es todo.",
        "Convierte tus obstáculos en escalones.",
        "El esfuerzo de hoy es el resultado de mañana.",
        "Sé más fuerte que tu excusa más fuerte.",
        "Nada cambia si nada cambia.",
        "El único fracaso real es dejar de intentarlo.",
        "Confía en el proceso.",
        "Tu mente es tu herramienta más poderosa.",
        "Cada obstáculo es una oportunidad disfrazada.",
        "La motivación te inicia, el hábito te mantiene.",
        "Sueña en grande, empieza en pequeño.",
        "El tiempo pasa igual, aprovéchalo.",
        "Levántate, ajusta tu corona y sigue.",
        "Nunca es tarde para ser quien quieras ser.",
        "El mejor momento para empezar fue ayer, el segundo mejor es hoy."
    )

    private val images = listOf(
        R.drawable.images,
        R.drawable.images__1_,
        R.drawable.images__2_,
        R.drawable.images__3_,
        R.drawable._2564357d23656a17fa7a1b643e724f29
    )

    val quotes: List<Quote> = (1..30).map { day ->
        Quote(
            day = day,
            text = phrases[(day - 1) % phrases.size],
            imageRes = images[(day - 1) % images.size]
        )
    }
}

@Composable
fun QuoteList(quotes: List<Quote>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF1F8F0)),
        contentPadding = PaddingValues(12.dp)
    ) {
        items(quotes) { quote ->
            QuoteCard(quote = quote, modifier = Modifier.padding(bottom = 12.dp))
        }
    }
}

@Composable
fun QuoteCard(quote: Quote, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = quote.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                )
                Text(
                    text = "Día ${quote.day}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = quote.text,
                        fontSize = 16.sp
                    )
                }
            }
            if (!expanded) {
                Text(
                    text = "Toca para ver la frase",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteListPreview() {
    MotivationalQuotesTheme {
        QuoteList(quotes = DataSource.quotes)
    }
}