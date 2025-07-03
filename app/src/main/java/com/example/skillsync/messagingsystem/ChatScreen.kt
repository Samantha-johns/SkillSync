package com.example.skillsync.messagingsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import com.example.skillsync.messagingsystem.Message
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    userName: String,
    currentUserId: String = "",
    otherUserId: String
) {
    val messages = remember { mutableStateListOf<Message>() }
    var messageText by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat with @$userName") }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BasicTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFF0F0F0))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send)
                )

                Button(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            scope.launch {
                                messages.add(Message(currentUserId, messageText))
                                messageText = ""
                            }
                        }
                    }
                ) {
                    Text("Send")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (messages.isEmpty()) {
                Text("Start the conversation 💬", color = Color.Gray, fontSize = 16.sp)
            } else {
                messages.forEach { message ->
                    Text(
                        text = "${if (message.senderId == currentUserId) "You" 
                        else userName}: ${message.text}",
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}









//package com.example.skillsync.messagingsystem
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import kotlinx.coroutines.tasks.await
//
//@Composable
//fun ChatScreen(
//    chatId: String,
//    userName: String,
//    currentUserId: String,
//    otherUserId: String
//) {
//    val db = FirebaseFirestore.getInstance()
//
//    var messages by remember { mutableStateOf<List<Message>>(emptyList()) }
//    var newMessage by remember { mutableStateOf("") }
//
//    // 🔁 Listen for real-time updates
//    LaunchedEffect(chatId) {
//        db.collection("chats")
//            .document(chatId)
//            .collection("messages")
//            .orderBy("timestamp", Query.Direction.ASCENDING)
//            .addSnapshotListener { snapshot, _ ->
//                messages = snapshot?.toObjects(Message::class.java) ?: emptyList()
//            }
//    }
//
//    Column(modifier = Modifier.fillMaxSize()) {
//
//        // 👤 Header
//        Text(
//            text = "Chat with $userName",
//            style = MaterialTheme.typography.titleMedium,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        // 📨 Messages
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .padding(horizontal = 8.dp)
//        ) {
//            items(messages) { message ->
//                ChatBubble(message = message, isMe = message.senderId == currentUserId)
//            }
//        }
//
//        // 💬 Message input
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            OutlinedTextField(
//                value = newMessage,
//                onValueChange = { newMessage = it },
//                placeholder = { Text("Type a message...") },
//                modifier = Modifier.weight(1f)
//            )
//
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Button(onClick = {
//                if (newMessage.isNotBlank()) {
//                    val message = Message(senderId = currentUserId, text = newMessage)
//                    db.collection("chats")
//                        .document(chatId)
//                        .collection("messages")
//                        .add(message)
//                    newMessage = ""
//                }
//            }) {
//                Text("Send")
//            }
//        }
//    }
//}
//
//@Composable
//fun ChatBubble(message: Message, isMe: Boolean) {
//    val bubbleColor = if (isMe) MaterialTheme.colorScheme.primary else Color.LightGray
//    val textColor = if (isMe) Color.White else Color.Black
//    val alignment = if (isMe) Arrangement.End else Arrangement.Start
//
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = alignment
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(4.dp)
//                .background(bubbleColor, shape = MaterialTheme.shapes.medium)
//                .padding(10.dp)
//        ) {
//            Text(text = message.text, color = textColor)
//        }
//    }
//}
