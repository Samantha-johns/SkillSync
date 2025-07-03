package com.example.skillsync.drawersections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skillsync.data.SkillRequest

@Composable
fun HomeScreen() {
    val mockRequests = listOf(
        SkillRequest("UI Design Help", "Looking for a partner to redesign a school project app", "Alice"),
        SkillRequest("Android Dev Partner", "Need help building a Compose-based UI", "Brian"),
        SkillRequest("Presentation Partner", "Someone to create clean slides for our business plan", "Nia"),
    )

    val tips = listOf(
        "🧠 Tip: Keep your requests clear and short!",
        "🚀 Tip: Reply fast to increase your chances!",
        "💬 Tip: Use the inbox to follow up after matching!"
    )

    var currentTipIndex by remember { mutableStateOf(0) }

    // Auto-switch tips every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            kotlinx.coroutines.delay(3000)
            currentTipIndex = (currentTipIndex + 1) % tips.size
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("🔥 Quick Tips", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.padding(16.dp)) {
                Text(tips[currentTipIndex])
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("📢 Trending Skill Requests", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(mockRequests.size) { index ->
                val request = mockRequests[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(request.title, fontSize = 18.sp,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight)
                        Text(request.description,
                            style = MaterialTheme.typography.bodyMedium)
                        Text("Posted by: ${request.authorName}",
                            style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}



//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@Composable
//fun HomeScreen() {
//    val tips = listOf(
//        "🧠 Be specific when posting your project or idea.",
//        "⏰ Collaboration works best when deadlines are clear.",
//        "🔍 Use keywords to help others discover your request.",
//        "💬 Respond to replies promptly to keep momentum.",
//        "🌟 Upgrade your profile to get noticed by top talent!"
//    )
//
//    val mockSkillRequests = listOf(
//        "Need a UI designer for a school budgeting app (Figma or Compose).",
//        "Looking for someone to review my data analysis project (Python/Pandas).",
//        "Group project – Help needed in HTML/CSS layout work 💻.",
//        "Wanna collab on a mental health awareness animation? DM me 🎨.",
//        "Can someone help with a basic Firebase setup? 🙏"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Tips for SkillSync 👇🏽",
//            style = MaterialTheme.typography.titleMedium,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        LazyColumn(modifier = Modifier.height(150.dp)) {
//            items(tips) { tip ->
//                Text("• $tip", fontSize = 14.sp, modifier = Modifier.padding(vertical = 2.dp))
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "Trending Skill Requests 💼",
//            style = MaterialTheme.typography.titleMedium,
//            modifier = Modifier.padding(bottom = 8.dp)
//        )
//
//        LazyColumn(modifier = Modifier.fillMaxHeight()) {
//            items(mockSkillRequests) { request ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//                ) {
//                    Box(modifier = Modifier.padding(12.dp)) {
//                        Text(text = request)
//                    }
//                }
//            }
//        }
//    }
//}


//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.skillsync.R
//
//@Composable
//fun HomeScreen() {
//    val mockTips = listOf(
//        "👩🏽‍💻 Practice coding daily!",
//        "🎯 Collaborate to grow fast",
//        "📚 Build your portfolio early",
//        "🚀 Ask questions fearlessly"
//    )
//
//    val mockRequests = listOf(
//        "Need help editing a documentary 🎬",
//        "Looking for a UI designer for my app 💡",
//        "Need someone to teach me Python 🐍",
//        "Searching for a partner for a school project 📘"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//        Text(
//            text = "Welcome to SkillSync 💜",
//            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.Bold
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(
//            text = "💡 Tips of the Day",
//            style = MaterialTheme.typography.titleMedium,
//            fontWeight = FontWeight.SemiBold
//        )
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//            items(mockTips) { tip ->
//                Card(
//                    modifier = Modifier
//                        .width(240.dp)
//                        .height(100.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//                    )
//                ) {
//                    Box(modifier = Modifier.padding(12.dp)) {
//                        Text(text = tip, fontSize = 16.sp)
//                    }
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(30.dp))
//
//        Text(
//            text = "🔥 Trending Skill Requests",
//            style = MaterialTheme.typography.titleMedium,
//            fontWeight = FontWeight.SemiBold
//        )
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        mockRequests.forEach { request ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 6.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = MaterialTheme.colorScheme.secondaryContainer
//                )
//            ) {
//                Box(modifier = Modifier.padding(16.dp)) {
//                    Text(text = request, fontSize = 16.sp)
//                }
//            }
//        }
//    }
//}
