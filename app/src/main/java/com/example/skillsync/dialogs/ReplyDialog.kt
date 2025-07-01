package com.example.skillsync.dialogs


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skillsync.data.Reply
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Composable
fun ReplyDialog(
    requestId: String,
    onDismiss: () -> Unit,
    onReplySent: () -> Unit
) {
    var message by remember { mutableStateOf("") }
    val user = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (message.isNotBlank() && user != null) {
                        val reply = Reply(
                            id = UUID.randomUUID().toString(),
                            requestId = requestId,
                            responderId = user.uid,
                            responderName = user.displayName ?: "Anonymous",
                            message = message,
                            timestamp = System.currentTimeMillis()
                        )
                        db.collection("requests")
                            .document(requestId)
                            .collection("replies")
                            .document(reply.id)
                            .set(reply)
                            .addOnSuccessListener {
                                onReplySent()
                                onDismiss()
                            }
                    }
                }
            ) {
                Text("Send")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Write a Reply") },
        text = {
            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = { Text("Your message") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}
