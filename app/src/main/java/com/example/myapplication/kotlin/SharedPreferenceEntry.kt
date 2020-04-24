package com.example.myapplication.kotlin

import java.util.*

/**
 *  Model class containing personal information that will be saved to SharedPreferences.
 */
data class SharedPreferenceEntry(
        val name: String, // Name of the User
        val dateOfBirth: Calendar, // Date of Birth of the user.
        val email: String // Email address of the user.
)