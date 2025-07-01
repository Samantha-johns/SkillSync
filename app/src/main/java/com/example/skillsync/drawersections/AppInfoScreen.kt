package com.example.skillsync.drawersections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppInfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("About SkillSync", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "SkillSync is a collaborative platform that connects students and creators " +
                    "with complementary skills to work on academic projects, creative ideas, and more.\n",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "✨ Key Features:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text("• Post and respond to skill requests")
        Text("• Search for collaborators by skill")
        Text("• Build a public profile with portfolio links")
        Text("• Track your posted activities")

        Spacer(modifier = Modifier.height(24.dp))

        Text("Version 1.0.0", style = MaterialTheme.typography.labelMedium)
        Text("Built with 💜 using Jetpack Compose & Firebase",
            style = MaterialTheme.typography.labelSmall)
    }
}
