//package com.example.skillsync.drawersections
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.example.skillsync.R
//import com.google.firebase.auth.FirebaseAuth
//
//@Composable
//fun LogoutScreen(navController: NavController) {
//    // Sign out as soon as screen loads
//    LaunchedEffect(Unit) {
//        FirebaseAuth.getInstance().signOut()
//    }
//
//    Scaffold { padding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding),
//            contentAlignment = Alignment.Center
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // You can use a cute tick icon here
//                Image(
//                    painter = painterResource(id = R.drawable.skillsync), // optional success icon
//                    contentDescription = "Logged Out",
//                    modifier = Modifier.size(100.dp)
//                )
//
//                Text(
//                    text = "You’ve been logged out",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = MaterialTheme.colorScheme.primary
//                )
//
//                Button(onClick = {
//                    navController.navigate("login") {
//                        popUpTo("dashboard") { inclusive = true }
//                    }
//                }) {
//                    Text("Go to Login")
//                }
//            }
//        }
//    }
//}


package com.example.skillsync.drawersections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LogoutScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Are you sure you want to logout?")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            auth.signOut()
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Logout")
        }
    }
}

