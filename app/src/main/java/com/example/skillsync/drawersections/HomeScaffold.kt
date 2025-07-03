package com.example.skillsync.drawersections

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.filled.Upgrade
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.skillsync.navigation.NavGraph
import com.example.skillsync.drawersections.LogoutScreen
import com.example.skillsync.drawersections.UpgradeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScaffold(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "SkillSync Menu",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )

                NavigationDrawerItem(
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    selected = false,
                    onClick = {
                        navController.navigate("home")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("My Activity") },
                    icon = { Icon(Icons.Default.Edit, contentDescription = null) },
                    selected = false,
                    onClick = {
                        navController.navigate("my_activity")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Upgrade") },
                  icon = { Icon(Icons.Default.Star, contentDescription = null) },

                            selected = false,
                    onClick = {
                        navController.navigate("upgrade")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("App Info") },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    selected = false,
                    onClick = {
                        navController.navigate("app_info")
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Logout") },
//                    icon = { Icon(Icons.Default.Logout, contentDescription = null) },
                    icon = { Icon(Icons.Default.ExitToApp, contentDescription = null) },

                            selected = false,
                    onClick = {
                        navController.navigate("logout")
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("SkillSync") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavGraph(
                navController = navController,
                startDestination = "home", // ✅ Fix
                modifier = Modifier.padding(innerPadding)
            )
        }

    }
}




//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.compose.material3.DrawerValue
//import androidx.compose.material3.rememberDrawerState
//import androidx.compose.material3.ModalNavigationDrawer
//import androidx.compose.material3.NavigationDrawerItem
//import androidx.compose.material3.NavigationDrawerItemDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.rememberTopAppBarScrollState
//import androidx.compose.material3.TopAppBarDefaults
//import com.example.skillsync.screens.BrowseRequestsScreen
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.TopAppBarScrollBehavior
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//import androidx.compose.runtime.rememberCoroutineScope
//
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScaffold(navController: NavController) {
//    val drawerState = rememberDrawerState(DrawerValue.Closed)
//    val scope = rememberCoroutineScope()
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet {
//                Text(
//                    "SkillSync",
//                    style = MaterialTheme.typography.titleLarge,
//                    modifier = Modifier.padding(16.dp)
//                )
//
//                NavigationDrawerItem(
//                    label = { Text("My Activity") },
//                    selected = false,
//                    onClick = { navController.navigate("my_activity") },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//
//                NavigationDrawerItem(
//                    label = { Text("App Info") },
//                    selected = false,
//                    onClick = { navController.navigate("app_info") },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//
//                NavigationDrawerItem(
//                    label = { Text("Upgrade") },
//                    selected = false,
//                    onClick = { navController.navigate("upgrade") },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//
//                NavigationDrawerItem(
//                    label = { Text("Logout") },
//                    selected = false,
//                    onClick = { navController.navigate("logout") },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//            }
//        }
//    ) {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text("SkillSync") },
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            scope.launch { drawerState.open() }
//                        }) {
//                            Icon(Icons.Default.Menu, contentDescription = "Menu")
//                        }
//                    },
//                    scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
//                        rememberTopAppBarScrollState()
//                    )
//                )
//            }
//        ) { paddingValues ->
//            BrowseRequestsScreen(modifier = Modifier.padding(paddingValues))
//        }
//    }
//}
//
//
//
//
//
//







//package com.example.skillsync.drawersections
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.skillsync.messagingsystem.ChatScreen
//import com.example.skillsync.messagingsystem.InboxScreen
//import com.example.skillsync.dashboard.DashboardScreen
//import com.example.skillsync.screens.ProfileScreen
//import com.example.skillsync.navigation.AppDrawer
//import com.example.skillsync.drawersections.UpgradeScreen
//import com.example.skillsync.searchfeature.SearchScreen
//import com.example.skillsync.navigation.BottomNavBar
//import com.google.firebase.auth.FirebaseAuth
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScaffold(navController: NavHostController) {
//    val currentUser = FirebaseAuth.getInstance().currentUser
//    val currentUserId = currentUser?.uid ?: ""
//
//    ModalNavigationDrawer(
//        drawerContent = {
//            AppDrawer(
//                onNavigate = { route -> navController.navigate(route) },
//                onLogout = {
//                    FirebaseAuth.getInstance().signOut()
//                    navController.navigate("login") {
//                        popUpTo("dashboard") { inclusive = true }
//                    }
//                }
//            )
//        }
//    ) {
//        Scaffold(
//            bottomBar = { BottomNavBar(navController) }
//        ) { paddingValues ->
//            NavHost(
//                navController = navController,
//                startDestination = "dashboard",
//                modifier = Modifier.padding(paddingValues)
//            ) {
//                composable("dashboard") {
//                    DashboardScreen(navController)
//                }
//
//                composable("inbox") {
//                    InboxScreen(
//                        onChatClick = { chatId, userName ->
//                            navController.navigate("chat/$chatId/$userName")
//                        }
//                    )
//                }
//
//                composable("chat/{chatId}/{userName}") { backStackEntry ->
//                    val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
//                    val userName = backStackEntry.arguments?.getString("userName") ?: ""
//                    ChatScreen(
//                        chatId = chatId,
//                        currentUserId = currentUserId,
//                        otherUserId = "", // You can enhance this later
//                        userName = userName
//                    )
//                }
//                composable("search") {
//                    SearchScreen(navController)
//                }
//
//
//                composable("profile") {
//                    ProfileScreen(navController)
//                }
//
//                composable("my_activity") {
//                    MyActivityScreen()
//                }
//
//                composable("upgrade") {
//                    UpgradeScreen()
//                }
//            }
//        }
//    }
//}
//
//




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
