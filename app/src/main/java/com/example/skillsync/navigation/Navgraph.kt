package com.example.skillsync.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skillsync.dialogs.PostRequestScreen
import com.example.skillsync.screens.LoginScreen
import com.example.skillsync.screens.RegisterScreen
import com.example.skillsync.screens.ForgotPasswordScreen
import com.example.skillsync.drawersections.HomeScaffold
import com.example.skillsync.drawersections.LogoutScreen
import com.example.skillsync.drawersections.MyActivityScreen
import com.example.skillsync.drawersections.UpgradeScreen
import com.example.skillsync.messagingsystem.InboxScreen
import com.example.skillsync.screens.BrowseRequestsScreen
import com.example.skillsync.screens.StartScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "start"

    ) {
        composable("start") {
            StartScreen(navController)
        }

//        composable("start") {
//            // Optional: StartScreen() if you have a splash/start screen
//            RegisterScreen(navController)
//        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("forgot") {
            ForgotPasswordScreen(navController)
        }

        composable("home") {
            HomeScaffold(navController)
        }
        composable("browse_requests") {
            BrowseRequestsScreen()
        }
        composable("upgrade") {
            UpgradeScreen()
        }
        composable("post_request") {
            PostRequestScreen()
        }
        composable("my_activity") {
            MyActivityScreen()
        }
        composable("inbox") {
            InboxScreen(
                onChatClick = { chatId, userName ->
                    navController.navigate("chat/$chatId/$userName")
                }
            )
        }
        composable("logout") {
            LogoutScreen(navController)
        }






    }
}
