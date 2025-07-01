package com.example.skillsync.drawersections

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.skillsync.drawersections.UpgradeInfo

@Composable
fun UpgradeScreen() {
    val user = FirebaseAuth.getInstance().currentUser
    val userId = user?.uid
    val db = FirebaseFirestore.getInstance()

    var bio by remember { mutableStateOf("") }
    var portfolioUrl by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    LaunchedEffect(userId) {
        if (userId != null) {
            db.collection("users").document(userId)
                .collection("upgradeInfo").document("info")
                .get()
                .addOnSuccessListener { doc ->
                    val data = doc.toObject(UpgradeInfo::class.java)
                    if (data != null) {
                        bio = data.bio
                        portfolioUrl = data.portfolioUrl
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Upgrade Your Profile", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = bio,
            onValueChange = { bio = it },
            label = { Text("Short Bio") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = portfolioUrl,
            onValueChange = { portfolioUrl = it },
            label = { Text("Portfolio URL (GitHub, Dribbble...)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (userId != null) {
                    isLoading = true
                    val upgradeInfo = UpgradeInfo(bio, portfolioUrl)
                    db.collection("users").document(userId)
                        .collection("upgradeInfo").document("info")
                        .set(upgradeInfo)
                        .addOnSuccessListener {
                            message = "Profile upgraded successfully 🎉"
                        }
                        .addOnFailureListener {
                            message = "Failed to save: ${it.message}"
                        }
                        .addOnCompleteListener {
                            isLoading = false
                        }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text("Save")
        }

        if (message.isNotEmpty()) {
            Text(message, color = MaterialTheme.colorScheme.primary)
        }
    }
}
