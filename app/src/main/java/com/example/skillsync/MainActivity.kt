package com.example.skillsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.compose.rememberNavController
import com.example.skillsync.navigation.NavGraph
import com.example.skillsync.ui.theme.SkillSyncTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SkillSyncTheme {
                val navController = rememberNavController()
                val isUserLoggedIn = remember { mutableStateOf<Boolean?>(null) }

                LaunchedEffect(Unit) {
                    val user = FirebaseAuth.getInstance().currentUser
                    isUserLoggedIn.value = user != null
                }

                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides this
                ) {
                    isUserLoggedIn.value?.let { loggedIn ->
                        NavGraph(
                            navController = navController,
                            startDestination = if (loggedIn) "home" else "login"
                        )
                    }
                }
            }
        }
    }
}



