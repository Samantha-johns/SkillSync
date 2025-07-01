package com.example.skillsync.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skillsync.data.SkillRequest
import com.example.skillsync.data.cards.RequestCard
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseRequestsScreen() {
    val db = FirebaseFirestore.getInstance()
    var requests by remember { mutableStateOf<List<SkillRequest>>(emptyList()) }

    LaunchedEffect(Unit) {
        db.collection("requests")
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    requests = it.documents.mapNotNull { doc ->
                        doc.toObject(SkillRequest::class.java)
                    }
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Browse Requests") })
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(requests) { request ->
                RequestCard(request = request)
            }
        }
    }
}
