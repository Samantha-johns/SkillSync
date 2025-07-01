package com.example.skillsync.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddRequestDialog(
    onDismiss: () -> Unit,
    onSubmit: (title: String, description: String, category: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank() && category.isNotBlank()) {
                        onSubmit(title, description, category)
                    }
                }
            ) {
                Text("Post")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("New Skill Request") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it },
                    label = { Text("Category (e.g. Design, Coding, Video)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}







//package com.example.skillsync.dialogs
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//
//@Composable
//fun AddRequestDialog(
//    onDismiss: () -> Unit,
//    onSubmit: (title: String, description: String) -> Unit
//) {
//    var title by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = {
//            Text("New Skill Request", style = MaterialTheme.typography.titleLarge)
//        },
//        text = {
//            Column {
//                OutlinedTextField(
//                    value = title,
//                    onValueChange = { title = it },
//                    label = { Text("Request Title") },
//                    singleLine = true,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                OutlinedTextField(
//                    value = description,
//                    onValueChange = { description = it },
//                    label = { Text("Description") },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(120.dp),
//                    maxLines = 5
//                )
//            }
//        },
//        confirmButton = {
//            Button(onClick = {
//                if (title.isNotBlank() && description.isNotBlank()) {
//                    onSubmit(title, description)
//                }
//                onDismiss()
//            }) {
//                Text("Post")
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = onDismiss) {
//                Text("Cancel")
//            }
//        }
//    )
//}


