package com.example.skillsync.dialogs


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.skillsync.data.SkillRequest
import java.util.*

@Composable
fun PostRequestScreen(
    onRequestPosted: () -> Unit = {}
) {
    val db = FirebaseFirestore.getInstance()
    val user = FirebaseAuth.getInstance().currentUser

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Post a New Skill Request", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Skill Category (e.g., Graphic Design, Coding)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank() && category.isNotBlank() && user != null) {
                    loading = true
                    val newRequest = SkillRequest(
                        id = UUID.randomUUID().toString(),
                        title = title,
                        description = description,
                        category = category,
                        authorId = user.uid,
                        authorName = user.displayName ?: "Anonymous",
                        timestamp = System.currentTimeMillis()
                    )

                    db.collection("requests")
                        .document(newRequest.id)
                        .set(newRequest)
                        .addOnSuccessListener {
                            message = "Request posted successfully!"
                            title = ""
                            description = ""
                            category = ""
                            onRequestPosted()
                        }
                        .addOnFailureListener {
                            message = "Error: ${it.message}"
                        }
                        .addOnCompleteListener {
                            loading = false
                        }
                } else {
                    message = "Please fill all fields."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        ) {
            Text("Submit Request")
        }

        if (message.isNotEmpty()) {
            Text(message, color = MaterialTheme.colorScheme.primary)
        }
    }
}
