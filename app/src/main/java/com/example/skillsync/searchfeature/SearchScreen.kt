package com.example.skillsync.searchfeature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.skillsync.data.User
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun SearchScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<User>>(emptyList()) }
    val db = FirebaseFirestore.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search by Skill") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            db.collection("users")
                .get()
                .addOnSuccessListener { snapshot ->
                    val allUsers = snapshot.documents.mapNotNull { it.toObject(User::class.java) }
                    searchResults = allUsers.filter { user ->
                        user.skills.any { it.contains(query.trim(), ignoreCase = true) }
                    }
                }
        }) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(searchResults) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .clickable {
                            navController.navigate("user_profile/${user.uid}")
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("${user.firstName} ${user.lastName}",
                            style = MaterialTheme.typography.titleMedium)
                        Text("Skills: ${user.skills.joinToString(", ")}",
                            style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
