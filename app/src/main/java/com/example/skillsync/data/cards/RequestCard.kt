package com.example.skillsync.data.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skillsync.data.Reply
import com.example.skillsync.data.SkillRequest
import com.example.skillsync.dialogs.ReplyDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RequestCard(request: SkillRequest) {
    val db = FirebaseFirestore.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val currentUserId = currentUser?.uid ?: return

    var showDialog by remember { mutableStateOf(false) }
    var replies by remember { mutableStateOf<List<Reply>>(emptyList()) }

    // 🔁 Load replies in real-time
    LaunchedEffect(request.id) {
        db.collection("requests")
            .document(request.id)
            .collection("replies")
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    replies = it.documents.mapNotNull { doc -> doc.toObject(Reply::class.java) }
                }
            }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = request.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = request.description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Posted by: ${request.authorName}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { showDialog = true }) {
                Text("Reply")
            }

            // 🗨 Show Replies
            if (replies.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text("Replies:", style = MaterialTheme.typography.titleSmall)
                replies.forEach { reply ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(reply.message)
                            Text(
                                "— ${reply.responderName}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        ReplyDialog(
            requestId = request.id,
            onDismiss = { showDialog = false },
            onReplySent = {
                // You can show a Toast or Snackbar later here if needed
                showDialog = false
            }
        )
    }
}

