package com.example.a03_05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a03_05.ui.theme._03_05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _03_05Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InputComponentsScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputComponentsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            var user by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var valid by remember { mutableStateOf("") }

            Text(
                "Login Details",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Build a login form (username + password fields).
            OutlinedTextField(
                value = user,
                onValueChange = {
                    user = it
                },
                label = { Text("User") },
                placeholder = { Text("Enter your username") },
                singleLine = true,
                // error handling
                isError = user.length < 3 && user.isEmpty(),
                supportingText = {
                    if (user.length < 3 && user.isNotEmpty()) {
                        Text("Enter valid User.",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password") },
                placeholder = { Text("Enter your password") },
                singleLine = true,
                // error handling
                isError = password.length < 3 && password.isNotEmpty(),
                supportingText = {
                    if (password.length < 3 && password.isNotEmpty()) {
                        Text("Enter valid Password.",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )

            Button(
                modifier = Modifier.padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally),
                    onClick = {
                        valid = if (password.isEmpty() || user.isEmpty()){
                            "Please fill all the fields"
                        } else{
                            "Welcome $user"
                        }
                    }

            ){
                Text("Submit")

            }
        Text(valid,
            modifier = Modifier.padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelLarge,
            color = if (valid.startsWith("Welcome")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _03_05Theme {
        InputComponentsScreen(
            modifier = Modifier.padding()
        )
    }
}