@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skillsync.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.skillsync.R


@Composable
fun StartScreen(navController: NavController) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                // 🟣 Logo (replace with your actual logo)
                Image(
                    painter = painterResource(id = R.drawable.skillsync), // 👈 your logo here
                    contentDescription = "App Logo",
                    modifier = Modifier.size(180.dp)
                )

                // 📝 App name
                Text(
                    text = "SkillSync",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                // ✨ Tagline
                Text(
                    text = "Connect. Collaborate. Create.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                // 🚀 Proceed button
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Continue")
                }
            }
        }
    }
}
