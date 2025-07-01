package com.example.skillsync.data

data class SkillRequest(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val timestamp: Long = 0L
)

//data class SkillRequest(
//    val id: String = "",
//    val title: String = "",
//    val description: String = "",
//    val postedBy: String = ""
//
//)
