@file:OptIn(ExperimentalMaterial3Api::class)


package com.example.skillsync.drawersections


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skillsync.data.Reply
import com.example.skillsync.data.SkillRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun MyActivityScreen() {
    val db = FirebaseFirestore.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser
    val uid = currentUser?.uid ?: return

    var myRequests by remember { mutableStateOf<List<SkillRequest>>(emptyList()) }
    var myReplies by remember { mutableStateOf<List<Reply>>(emptyList()) }

    // Load user's posted requests
    LaunchedEffect(uid) {
        db.collection("requests")
            .whereEqualTo("authorId", uid)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    myRequests = it.documents.mapNotNull { doc ->
                        doc.toObject(SkillRequest::class.java)
                    }
                }
            }

        // Fetch replies across all requests
        db.collectionGroup("replies")
            .whereEqualTo("responderId", uid)
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    myReplies = it.documents.mapNotNull { doc ->
                        doc.toObject(Reply::class.java)
                    }
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Activity") })
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("My Requests", style = MaterialTheme.typography.titleMedium)
            }
            items(myRequests) { request ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(request.title, style = MaterialTheme.typography.titleSmall)
                        Text(request.description, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text("My Replies", style = MaterialTheme.typography.titleMedium)
            }
            items(myReplies) { reply ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(reply.message, style = MaterialTheme.typography.bodyMedium)
                        Text("To Request ID: ${reply.requestId}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}



//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.skillsync.data.SkillRequest
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//
//@Composable
//fun MyActivityScreen() {
//    val db = FirebaseFirestore.getInstance()
//    val auth = FirebaseAuth.getInstance()
//    val currentUser = auth.currentUser?.email ?: "unknown"
//
//    var myRequests by remember { mutableStateOf<List<SkillRequest>>(emptyList()) }
//
//    // Fetch only this user's requests
//    LaunchedEffect(Unit) {
//        db.collection("requests")
//            .whereEqualTo("postedBy", currentUser)
//            .get()
//            .addOnSuccessListener { snapshot ->
//                myRequests = snapshot.documents.mapNotNull { it.toObject(SkillRequest::class.java) }
//            }
//    }
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//
//        Text("My Posted Requests", style = MaterialTheme.typography.headlineSmall)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        if (myRequests.isEmpty()) {
//            Text("You haven’t posted any requests yet.", style = MaterialTheme.typography.bodyMedium)
//        } else {
//            LazyColumn {
//                items(myRequests) { request ->
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(bottom = 12.dp),
//                        elevation = CardDefaults.cardElevation(4.dp)
//                    ) {
//                        Column(modifier = Modifier.padding(16.dp)) {
//                            Text(request.title, style = MaterialTheme.typography.titleMedium)
//                            Spacer(modifier = Modifier.height(4.dp))
//                            Text(request.description, style = MaterialTheme.typography.bodySmall)
//                        }
//                    }
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // 💬 Placeholder for Replies (Coming Soon)
//        Text("Replies Made (Coming Soon)", style = MaterialTheme.typography.titleSmall)
//        Text("We’ll list comments/replies you’ve made to others’ posts here.", style = MaterialTheme.typography.bodySmall)
//    }
//}
