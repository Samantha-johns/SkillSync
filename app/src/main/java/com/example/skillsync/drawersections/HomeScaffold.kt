package com.example.skillsync.drawersections

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skillsync.messagingsystem.ChatScreen
import com.example.skillsync.messagingsystem.InboxScreen
import com.example.skillsync.dashboard.DashboardScreen
import com.example.skillsync.screens.ProfileScreen
import com.example.skillsync.navigation.AppDrawer
import com.example.skillsync.drawersections.UpgradeScreen
import com.example.skillsync.searchfeature.SearchScreen
import com.example.skillsync.navigation.BottomNavBar
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(navController: NavHostController) {
    val currentUser = FirebaseAuth.getInstance().currentUser
    val currentUserId = currentUser?.uid ?: ""

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                onNavigate = { route -> navController.navigate(route) },
                onLogout = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
    ) {
        Scaffold(
            bottomBar = { BottomNavBar(navController) }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "dashboard",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("dashboard") {
                    DashboardScreen(navController)
                }

                composable("inbox") {
                    InboxScreen(
                        onChatClick = { chatId, userName ->
                            navController.navigate("chat/$chatId/$userName")
                        }
                    )
                }

                composable("chat/{chatId}/{userName}") { backStackEntry ->
                    val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
                    val userName = backStackEntry.arguments?.getString("userName") ?: ""
                    ChatScreen(
                        chatId = chatId,
                        currentUserId = currentUserId,
                        otherUserId = "", // You can enhance this later
                        userName = userName
                    )
                }
                composable("search") {
                    SearchScreen(navController)
                }


                composable("profile") {
                    ProfileScreen(navController)
                }

                composable("my_activity") {
                    MyActivityScreen()
                }

                composable("upgrade") {
                    UpgradeScreen()
                }
            }
        }
    }
}






//
//import androidx.compose.foundation.clickable
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.foundation.layout.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.graphics.vector.ImageVector
//import kotlinx.coroutines.launch
//import com.example.skillsync.dashboard.DashboardScreen
//import com.example.skillsync.navigation.AppDrawer
//import com.example.skillsync.searchfeature.SearchScreen
//import com.example.skillsync.screens.ProfileScreen
//
//
//@Composable
//fun HomeScaffold(navController: NavController) {
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//
//    // 🔁 This controls which screen is active
//    var currentScreen by remember { mutableStateOf("dashboard") }
//
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            AppDrawer(
//                navController = navController,
//                onNavigate = { screen ->
//                    currentScreen = screen
//                    scope.launch { drawerState.close() }
//                },
//                onLogout = {
//                    navController.navigate("login") {
//                        popUpTo("home") { inclusive = true }
//                    }
//                }
//            )
//        }
//    ) {
//        Scaffold(
//            topBar = {
//                SmallTopAppBar(
//                    title = { Text("SkillSync") },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            scope.launch { drawerState.open() }
//                        }) {
//                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
//                        }
//                    }
//                )
//            }
//        ) { paddingValues ->
//            Box(modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//            ) {
//                when (currentScreen) {
//                    "dashboard" -> DashboardScreen(navController)
//                    "search" -> SearchScreen()
//                    "profile" -> ProfileScreen(navController)
//                    "activity" -> MyActivityScreen() // build later
//                    "info" -> AppInfoScreen() // build later
//                    "upgrade" -> UpgradeScreen() // build later
//                }
//            }
//        }
//    }
//}
//@Composable
//fun DrawerItem(
//    icon: ImageVector,
//    label: String,
//    onClick: () -> Unit
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() }
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            imageVector = icon,
//            contentDescription = label,
//            modifier = Modifier.size(24.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Text(text = label, style = MaterialTheme.typography.bodyLarge)
//    }
//}
//


//
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import kotlinx.coroutines.launch

//
//@Composable
//fun HomeScaffold(navController: NavController) {
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//
//    var currentScreen by remember { mutableStateOf("dashboard") }
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            AppDrawer(
//                navController = navController,
//                onLogout = {
//                    // After logout, navigate back to login screen
//                    navController.navigate("login") {
//                        popUpTo("home") { inclusive = true }
//                    }
//                }
//            )
//        }
//    ) {
//        Scaffold(
//            topBar = {
//                SmallTopAppBar(
//                    title = { Text("SkillSync") },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            scope.launch { drawerState.open() }
//                        }) {
//                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
//                        }
//                    }
//                )
//            }
//        ) { paddingValues ->
//            Box(modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//            ) {
//                when (currentScreen) {
//                    "dashboard" -> DashboardScreen(navController)
//                    "search" -> SearchScreen()
//                    "profile" -> ProfileScreen(navController)
//                }
//            }
//        }
//    }
//}
