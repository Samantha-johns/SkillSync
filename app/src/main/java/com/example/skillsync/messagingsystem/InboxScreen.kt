package com.example.skillsync.messagingsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Ensure this import is present
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// The ChatPreview data class definition should be REMOVED from here,
// as it exists in ChatPreview.kt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InboxScreen(
    navController: NavController ,
    // Update the lambda parameter name to match the property in ChatPreview.kt
    onChatClick: (chatId: String, otherUserName: String) -> Unit = { _, _ -> }
) {
    val mockChats = listOf(
        ChatPreview( // Now uses the full ChatPreview structure from ChatPreview.kt
            chatId = "chat1",
            otherUserId = "liam_id", // Example: provide an ID or empty string
            otherUserName = "Liam",
            lastMessage = "Yo, did you finish the UI?",
            timestamp = System.currentTimeMillis() // Add current time or leave default 0L
        ),
        ChatPreview(
            chatId = "chat2",
            otherUserId = "emma_id",
            otherUserName = "Emma",
            lastMessage = "I'll send the files tonight",
            timestamp = System.currentTimeMillis()
        ),
        ChatPreview(
            chatId = "chat3",
            otherUserId = "noah_id",
            otherUserName = "Noah",
            lastMessage = "How’s the backend going?",
            timestamp = System.currentTimeMillis()
        ),
        ChatPreview(
            chatId = "chat4",
            otherUserId = "ava_id",
            otherUserName = "Ava",
            lastMessage = "Can we hop on a quick call?",
            timestamp = System.currentTimeMillis()
        ),
        ChatPreview(
            chatId = "chat5",
            otherUserId = "elijah_id",
            otherUserName = "Elijah",
            lastMessage = "I love the pitch idea! 🔥",
            timestamp = System.currentTimeMillis()
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inbox 📬") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // TODO: You can add a new chat screen later
            }) {
                Icon(Icons.Default.Add, contentDescription = "Start Chat")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            items(mockChats) { chat ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // Use the correct property name from ChatPreview.kt
                            onChatClick(chat.chatId, chat.otherUserName)
                        }
                        .padding(vertical = 10.dp)
                ) {
                    // Use the correct property name from ChatPreview.kt
                    Text(chat.otherUserName, style = MaterialTheme.typography.titleMedium)
                    Text(chat.lastMessage, fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                    HorizontalDivider(modifier = Modifier.padding(top = 10.dp))
                }
            }
        }
    }
}



//@file:OptIn(ExperimentalMaterial3Api::class)
//
//package com.example.skillsync.messagingsystem
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.skillsync.messagingsystem.ChatPreview
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.tasks.await
//
//
//
//@Composable
//fun InboxScreen(onChatClick: (String, String) -> Unit) {
//    val db = FirebaseFirestore.getInstance()
//    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: return
//
//    var inboxList by remember { mutableStateOf<List<ChatPreview>>(emptyList()) }
//
//    // Fetch Inbox data
//    LaunchedEffect(Unit) {
//        db.collection("inbox")
//            .document(currentUserId)
//            .collection("chats")
//            .addSnapshotListener { snapshot, _ ->
//                snapshot?.let {
//                    inboxList = it.documents.mapNotNull { doc ->
//                        doc.toObject(ChatPreview::class.java)
//                    }
//                }
//            }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Inbox") })
//        }
//    ) { padding ->
//        LazyColumn(
//            contentPadding = padding,
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxSize()
//        ) {
//            items(inboxList) { chat ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//                            onChatClick(chat.chatId, chat.otherUserName)
//                        }
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(chat.otherUserName, style = MaterialTheme.typography.titleMedium)
//                        Spacer(modifier = Modifier.height(4.dp))
//                        Text(chat.lastMessage, style = MaterialTheme.typography.bodySmall)
//                    }
//                }
//            }
//        }
//    }
//}
