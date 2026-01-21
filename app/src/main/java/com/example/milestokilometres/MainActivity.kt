package com.example.milestokilometres

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp

import com.example.milestokilometres.ui.theme.MilesToKilometresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MilesToKilometresTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MilesToKilometersConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MilesToKilometersConverter(modifier: Modifier = Modifier) {
    var milesInput by remember { mutableStateOf("") }
    var kilometers by remember { mutableStateOf("") }

    fun convertMilesToKilometers() {
        val miles = milesInput.toDoubleOrNull()
        kilometers = if (miles != null) {
            String.format(java.util.Locale.US, "%.2f", miles * 1.60934)
        } else {
            "Invalid"
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Miles to Kilometers")

        TextField(
            value = milesInput,
            onValueChange = { milesInput = it },
            label = { Text("Miles") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        Button(onClick = { convertMilesToKilometers() }) {
            Text("Convert")
        }

        if (kilometers.isNotEmpty()) {
            Text("Result: $kilometers km")
        }
    }
}