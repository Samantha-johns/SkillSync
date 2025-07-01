package com.example.skillsync.data

data class Reply(
    val id: String = "",
    val requestId: String = "",
    val responderId: String = "",
    val responderName: String = "",
    val message: String = "",
    val timestamp: Long = 0L
)
