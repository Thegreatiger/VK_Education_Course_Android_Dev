package vk.education.android.dev

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите текст или номер") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (text.isBlank()) {
                    Toast.makeText(context, "Введите текст", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val intent = Intent(context, SecondActivity::class.java).apply {
                    putExtra(SecondActivity.EXTRA_TEXT, text.trim())
                }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Открыть вторую Activity")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val phone = text.trim()
                when {
                    phone.isBlank() -> {
                        Toast.makeText(context, "Введите номер телефона", Toast.LENGTH_SHORT).show()
                    }
                    !isValidPhoneNumber(phone) -> {
                        Toast.makeText(
                            context,
                            "Некорректный номер. Разрешены цифры, +, -, (), пробелы (5–15 цифр)",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {
                        val cleanedNumber = phone.filter { it.isDigit() || it == '+' }
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = "tel:$cleanedNumber".toUri()
                        }
                        try {
                            context.startActivity(intent)
                        } catch (_: ActivityNotFoundException) {
                            Toast.makeText(
                                context,
                                "Нет приложения для звонков",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Позвонить другу")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val shareText = text.trim()
                if (shareText.isBlank()) {
                    Toast.makeText(context, "Введите текст для отправки", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                }
                try {
                    context.startActivity(Intent.createChooser(intent, "Поделиться через…"))
                } catch (_: ActivityNotFoundException) {
                    Toast.makeText(
                        context,
                        "Нет приложений для отправки",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поделиться текстом")
        }
    }
}

private fun isValidPhoneNumber(input: String): Boolean {
    val digits = input.filter { it.isDigit() }
    if (digits.length !in 5..15) return false
    return input.all { it.isDigit() || it in "+ -()" }
}