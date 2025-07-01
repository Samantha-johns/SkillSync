@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skillsync.messagingsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skillsync.messagingsystem.ChatPreview
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await



@Composable
fun InboxScreen(onChatClick: (String, String) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return

    var inboxList by remember { mutableStateOf<List<ChatPreview>>(emptyList()) }

    // Fetch Inbox data
    LaunchedEffect(Unit) {
        db.collection("inbox")
            .document(currentUserId)
            .collection("chats")
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    inboxList = it.documents.mapNotNull { doc ->
                        doc.toObject(ChatPreview::class.java)
                    }
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Inbox") })
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(inboxList) { chat ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onChatClick(chat.chatId, chat.otherUserName)
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(chat.otherUserName, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(chat.lastMessage, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
