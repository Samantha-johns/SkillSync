package com.example.skillsync.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val auth = FirebaseAuth.getInstance()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome Back to SkillSync 👋🏽",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        isLoading = true
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                } else {
                                    errorMessage = task.exception?.message
                                }
                            }
                    } else {
                        errorMessage = "Email and password cannot be empty"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            TextButton(onClick = {
                navController.navigate("forgot")
            }) {
                Text("Forgot Password?")
            }

            TextButton(onClick = {
                navController.navigate("register")
            }) {
                Text("Don't have an account? Register")
            }

            errorMessage?.let {
                Spacer(modifier = Modifier.height(12.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(60.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}









//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.google.firebase.auth.FirebaseAuth
//
//@Composable
//fun LoginScreen(navController: NavController) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//    val auth = FirebaseAuth.getInstance()
//
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Login to SkillSync", style = MaterialTheme.typography.headlineSmall)
//            Spacer(modifier = Modifier.height(24.dp))
//
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                modifier = Modifier.fillMaxWidth(),
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
//                visualTransformation = PasswordVisualTransformation()
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//            errorMessage?.let {
//                Text(text = it, color = MaterialTheme.colorScheme.error)
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//
//            Button(
//                onClick = {
//                    if (email.isNotBlank() && password.length >= 6) {
//                        auth.signInWithEmailAndPassword(email, password)
//                            .addOnCompleteListener { task ->
//                                if (task.isSuccessful) {
//                                    navController.navigate("dashboard") {
//                                        popUpTo("login") { inclusive = true }
//                                    }
//                                } else {
//                                    errorMessage = task.exception?.message ?: "Login failed"
//                                }
//                            }
//                    } else {
//                        errorMessage = "Please enter valid credentials."
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Login")
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            TextButton(onClick = {
//                navController.navigate("register")
//            }) {
//                Text("Don't have an account? Register")
//            }
//        }
//    }
//}
//
//







//package com.example.skillsync.screens
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.*
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.google.firebase.auth.FirebaseAuth
//
//@Composable
//fun LoginScreen(
//    navController: NavController,
//    onLoginSuccess: () -> Unit = {} // default for preview
//) {
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    val auth = FirebaseAuth.getInstance()
//
//    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(24.dp)
//        ) {
//            Text(
//                text = "Login to SkillSync",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 24.dp),
//                textAlign = TextAlign.Center
//            )
//
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                label = { Text("Email") },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                label = { Text("Password") },
//                visualTransformation = PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    if (email.isNotBlank() && password.isNotBlank()) {
//                        auth.signInWithEmailAndPassword(email, password)
//                            .addOnCompleteListener { task ->
//                                if (task.isSuccessful) {
//                                    println("✅ Login success: ${auth.currentUser?.uid}")
//                                    onLoginSuccess()
//                                    // OR: navController.navigate("dashboard")
//                                } else {
//                                    println("❌ Login failed: ${task.exception?.message}")
//                                }
//                            }
//                    } else {
//                        println("❌ Please fill in all fields")
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Login")
//            }
//            TextButton(onClick = { navController.navigate("forgot") }) {
//                Text("Forgot Password?")
//            }
//
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            TextButton(onClick = { navController.navigate("register") }) {
//                Text("Don't have an account? Register")
//            }
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//fun PreviewLoginScreen() {
//    LoginScreen(navController = rememberNavController())
//}

