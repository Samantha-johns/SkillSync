package com.example.skillsync.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun addSkillToUser(skill: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid

    if (uid != null) {
        val userDocRef = FirebaseFirestore.getInstance().collection("users").document(uid)

        userDocRef.get().addOnSuccessListener { document ->
            if (document.exists()) {
                val currentSkills = document.get("skills") as? List<String> ?: emptyList()
                val updatedSkills = if (skill !in currentSkills) currentSkills + skill else currentSkills

                userDocRef.update("skills", updatedSkills)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure(it) }
            } else {
                // If document doesn't exist, create one
                val newUser = mapOf("skills" to listOf(skill))
                userDocRef.set(newUser)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onFailure(it) }
            }
        }.addOnFailureListener { onFailure(it) }

    } else {
        onFailure(Exception("User not logged in"))
    }
}
