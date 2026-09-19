package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Parte superior: nombre y titulo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Camila",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD6336C)
            )
            Text(
                text = "Estudiante de Ingeniería de Sistemas",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF862E4F),
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Parte inferior: informacion de contacto
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ContactRow(label = "📞", info = "+51 123 456 78")
            ContactRow(label = "✉️", info = "camila.bada@unmsm.edu.pe")
            ContactRow(label = "🌐", info = "github.com/camilabada-lgtm")
        }
    }
}

@Composable
fun ContactRow(label: String, info: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = info,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}