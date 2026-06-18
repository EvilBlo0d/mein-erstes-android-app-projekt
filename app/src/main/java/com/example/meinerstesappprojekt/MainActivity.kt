package com.example.meinerstesappprojekt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meinerstesappprojekt.ui.theme.MeinErstesAppProjektTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeinErstesAppProjektTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var buttonClicks by remember { mutableIntStateOf(0) }
    var dailyMessage by remember {
        mutableStateOf("Drücke den Button für deine Tagesnachricht.")
    }

    val messages = listOf(
        "Heute ist ein guter Tag zum Lernen.",
        "Kleine Schritte bringen dich weiter.",
        "Du baust gerade deine erste eigene App.",
        "GitHub wird mit jedem Commit vertrauter.",
        "Weiter so, das sieht schon richtig gut aus."
    )

    val displayName = if (name.text.isBlank()){
        "Gast"
    } else {
        name.text
    }

    val currentDateTime = SimpleDateFormat(
        "dd.MM.yyyy HH:mm",
        Locale.getDefault()
    ).format(Date())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hallo $displayName!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Name eingeben")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "$currentDateTime",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = dailyMessage,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                buttonClicks++
                dailyMessage = messages.random()
            }
        ) {
            Text(text = "Neue Nachricht")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Du hast $buttonClicks mal geklickt.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    MeinErstesAppProjektTheme {
        StartScreen()
    }
}