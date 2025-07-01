package com.example.skillsync.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skillsync.R

@Composable
fun ProfileScreen(navController: NavController) {
    // Replace with real user data (from Firestore)
    val name = "Hailey Johns"
    val email = "hailey@example.com"
    val bio = "Creative thinker. Always learning. Passionate about digital design & teamwork."
    val workEthic = "Perfectionist 💯 | Team Player 🤝"
    val skills = listOf("Graphic Design", "Video Editing", "UI/UX")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Profile Picture (placeholder)
        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = name, style = MaterialTheme.typography.titleLarge)
        Text(text = email, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Bio", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Text(bio, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(12.dp))

        Text("Work Ethic", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        Text(workEthic, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(12.dp))

        Text("Skills", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        skills.forEach { skill ->
            AssistChip(
                onClick = { /* Maybe navigate to skill details later */ },
                label = { Text(skill) },
                modifier = Modifier.padding(end = 4.dp, bottom = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            // Navigate to edit screen (to build later)
            // navController.navigate("editProfile")
        }) {
            Text("Edit Profile")
        }
    }
}
