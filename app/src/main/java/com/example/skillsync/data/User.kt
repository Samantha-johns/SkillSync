package com.example.skillsync.data

data class User(
    val uid: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val bio: String = "",
    val skills: List<String> = emptyList(), // ✅ New
    val profileImageUrl: String = "" // Optional for later
)
