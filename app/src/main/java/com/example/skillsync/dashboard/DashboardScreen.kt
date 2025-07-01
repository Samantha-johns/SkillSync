package com.example.skillsync.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skillsync.data.SkillRequest
import com.example.skillsync.dialogs.AddRequestDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

@Composable
fun DashboardScreen(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()

    var showDialog by remember { mutableStateOf(false) }
    var requests by remember { mutableStateOf(listOf<SkillRequest>()) }

    // 🔁 Real-time request loading
    LaunchedEffect(Unit) {
        db.collection("requests")
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null && !snapshot.isEmpty) {
                    requests = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(SkillRequest::class.java)?.copy(id = doc.id)
                    }
                }
            }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Request")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Skill Requests", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(requests) { request ->
                    RequestCard(request)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            if (showDialog) {
                AddRequestDialog(
                    onDismiss = { showDialog = false },
                    onSubmit = { title, description, category ->
                        val user = auth.currentUser
                        val newRequest = SkillRequest(
                            title = title,
                            description = description,
                            category = category,
                            authorId = user?.uid ?: "",
                            authorName = user?.displayName ?: user?.email ?: "Anonymous",
                            timestamp = System.currentTimeMillis()
                        )
                        db.collection("requests").add(newRequest)
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun RequestCard(request: SkillRequest) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(request.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(request.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Category: ${request.category}", style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Posted by: ${request.authorName}", style = MaterialTheme.typography.labelSmall)
        }
    }
}







//package com.example.skillsync.dashboard
//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.skillsync.data.SkillRequest
//import com.example.skillsync.dialogs.AddRequestDialog
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//
//@Composable
//fun DashboardScreen(navController: NavController) {
//    val db = FirebaseFirestore.getInstance()
//    val auth = FirebaseAuth.getInstance()
//
//    var showDialog by remember { mutableStateOf(false) }
//    var requests by remember { mutableStateOf(listOf<SkillRequest>()) }
//
//    // Fetch requests in real-time
//    LaunchedEffect(Unit) {
//        db.collection("requests")
//            .orderBy("title", Query.Direction.ASCENDING)
//            .addSnapshotListener { snapshot, _ ->
//                if (snapshot != null && !snapshot.isEmpty) {
//                    requests = snapshot.documents.mapNotNull { doc ->
//                        doc.toObject(SkillRequest::class.java)?.copy(id = doc.id)
//                    }
//                }
//            }
//    }
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { showDialog = true }) {
//                Icon(Icons.Filled.Add, contentDescription = "Add Request")
//            }
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(16.dp)
//        ) {
//            Text("Skill Requests", style = MaterialTheme.typography.headlineMedium)
//            Spacer(modifier = Modifier.height(16.dp))
//
//            LazyColumn {
//                items(requests) { request ->
//                    RequestCard(request)
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            }
//
//            if (showDialog) {
//                AddRequestDialog(
//                    onDismiss = { showDialog = false },
//                    onSubmit = { title, description ->
//                        val currentUser = auth.currentUser?.email ?: "Anonymous"
//
//                        val newRequest = SkillRequest(
//                            title = title,
//                            description = description,
//                            postedBy = currentUser
//                        )
//
//                        db.collection("requests").add(newRequest)
//                        showDialog = false
//                    }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun RequestCard(request: SkillRequest) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(request.title, style = MaterialTheme.typography.titleMedium)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(request.description, style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(6.dp))
//            Text("Posted by: ${request.postedBy}", style = MaterialTheme.typography.labelSmall)
//        }
//    }
//}
//
//
//
//


//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//
//data class SkillRequest(
//    val title: String,
//    val description: String,
//    val postedBy: String
//)
//
//@Composable
//fun DashboardScreen(navController: NavController) {
//    // Sample list of requests (to be fetched from Firebase later)
//    val requests = listOf(
//        SkillRequest("Need help with video editing",
//            "Looking for someone good with Premiere Pro for a class project",
//            "JohnDoe"),
//        SkillRequest("Graphic design for poster",
//            "Need a clean event poster design by Sunday",
//            "Kelsa_Kreative"),
//        SkillRequest("App Testing Feedback",
//            "Looking for users to test my UI flow and give honest feedback",
//            "DevQueen123")
//    )
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                // Navigate or show dialog to add a request
//                // navController.navigate("addRequest")
//            }) {
//                Icon(Icons.Filled.Add, contentDescription = "Add Request")
//            }
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(16.dp)
//        ) {
//            Text("Skill Requests", style = MaterialTheme.typography.headlineMedium)
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            LazyColumn {
//                items(requests) { request ->
//                    RequestCard(request = request)
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun RequestCard(request: SkillRequest) {
//    Card(
//        modifier = Modifier.fillMaxWidth(),
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(request.title, style = MaterialTheme.typography.titleMedium)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(request.description, style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(6.dp))
//            Text("Posted by @${request.postedBy}", style = MaterialTheme.typography.labelSmall)
//        }
//    }
//}
