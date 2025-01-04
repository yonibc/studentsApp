package com.example.studentsapp.model

data class Student(
    var name: String,
    val id: String,
    val avatarUrl: String = "",
    var isChecked: Boolean,
    var phone: String,
    var address: String
)
