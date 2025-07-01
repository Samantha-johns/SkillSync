package com.example.skillsync.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppDrawer(
    onNavigate: (String) -> Unit,
    onLogout: () -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser
    val displayName = user?.displayName ?: "SkillSync User"
    val email = user?.email ?: "No email"
    val profilePic = user?.photoUrl?.toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 🔵 Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (profilePic != null) {
                Image(
                    painter = rememberAsyncImagePainter(profilePic),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Default Avatar",
                    tint = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(displayName, color = Color.White, style = MaterialTheme.typography.titleMedium)
            Text(email, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🧭 Navigation Items
        DrawerItem(
            icon = Icons.Default.Home,
            label = "Dashboard",
            onClick = { onNavigate("dashboard") }
        )

        DrawerItem(
            icon = Icons.Default.Add,
            label = "New Request",
            onClick = { onNavigate("post_request") }
        )

        DrawerItem(
            icon = Icons.AutoMirrored.Filled.List,
            label = "My Activity",
            onClick = { onNavigate("my_activity") }
        )

        DrawerItem(
            icon = Icons.Default.Info,
            label = "App Info",
            onClick = { onNavigate("app_info") }
        )

        DrawerItem(
            icon = Icons.Default.Star,
            label = "Upgrade",
            onClick = { onNavigate("upgrade") }
        )

        Spacer(modifier = Modifier.weight(1f))

        // 🔴 Logout
        HorizontalDivider()
        DrawerItem(
            icon = Icons.AutoMirrored.Filled.ExitToApp,
            label = "Logout",
            onClick = onLogout
        )
    }
}

@Composable
fun DrawerItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
//import androidx.compose.material.icons.automirrored.filled.ExitToApp
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberAsyncImagePainter
////import com.example.skillsync.drawersections.DrawerItem
//import com.google.firebase.auth.FirebaseAuth
//
//@Composable
//fun AppDrawer(
//    onNavigate: (String) -> Unit,
//    onLogout: () -> Unit
//) {
//    val user = FirebaseAuth.getInstance().currentUser
//    val displayName = user?.displayName ?: "SkillSync User"
//    val email = user?.email ?: "No email"
//    val profilePic = user?.photoUrl?.toString()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        // 🔵 Header
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.primary)
//                .padding(24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            if (profilePic != null) {
//                Image(
//                    painter = rememberAsyncImagePainter(profilePic),
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(CircleShape)
//                )
//            } else {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = "Default Avatar",
//                    tint = Color.White,
//                    modifier = Modifier
//                        .size(80.dp)
//                        .clip(CircleShape)
//                        .background(Color.Gray)
//                        .padding(12.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(displayName, color = Color.White, style = MaterialTheme.typography.titleMedium)
//            Text(email, color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.bodySmall)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // 🧭 Drawer Items
//        DrawerItem(
//            icon = Icons.Default.Home,
//            label = "Dashboard",
//            onClick = { onNavigate("dashboard") }
//        )
//
//        DrawerItem(
//            icon = Icons.Default.Add,
//            label = "New Request",
//            onClick = { onNavigate("post_request") }
//        )
//
//
//        DrawerItem(
//            icon = Icons.Default.List,
//            label = "My Activity",
//            onClick = { onNavigate("browse_requests") } // You can change this to "my_activity"
//        )
//
//        DrawerItem(
//            icon = Icons.Default.Info,
//            label = "App Info",
//            onClick = { onNavigate("app_info") }
//        )
//
//        DrawerItem(
//            icon = Icons.Default.Star,
//            label = "Upgrade",
//            onClick = { onNavigate("upgrade") }
//        )
//
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        // 🔴 Logout
//        Divider()
//        DrawerItem(
//            icon = Icons.AutoMirrored.Filled.ExitToApp,
//            label = "Logout",
//            onClick = onLogout
//        )
//    }
//}




//@Composable
//fun AppDrawer(
//    navController: NavController,
//    onLogout: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxHeight()
//            .background(MaterialTheme.colorScheme.surfaceVariant)
//            .padding(16.dp)
//    ) {
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Header
//        Image(
//            imageVector = Icons.Filled.Person,
//            contentDescription = "User",
//            modifier = Modifier
//                .size(64.dp)
//                .background(Color.Gray, shape = CircleShape)
//                .padding(8.dp)
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//        Text("Logged in as", style = MaterialTheme.typography.labelSmall)
//        Text(FirebaseAuth.getInstance().currentUser?.email ?: "Unknown",
//            style = MaterialTheme.typography.titleSmall)
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Navigation Items
//        DrawerItem(label = "My Activity") { /* navController.navigate("myActivity") */ }
//        DrawerItem(label = "App Info", icon = Icons.Default.Info) { /* navController.navigate("appInfo") */ }
//        DrawerItem(label = "Upgrade Profile") { /* navController.navigate("upgrade") */ }
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        // Logout
//        DrawerItem(label = "Logout", icon = Icons.Default.ExitToApp) {
//            FirebaseAuth.getInstance().signOut()
//            onLogout()
//        }
//    }
//}
//
//@Composable
//fun DrawerItem(label: String,
//               icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
//               onClick: () -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 12.dp)
//            .clickable { onClick() }
//    ) {
//        if (icon != null) {
//            Icon(icon, contentDescription = null, modifier = Modifier.padding(end = 12.dp))
//        }
//        Text(text = label, style = MaterialTheme.typography.bodyLarge)
//    }
//}
