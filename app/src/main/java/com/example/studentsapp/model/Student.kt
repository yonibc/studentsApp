package com.example.studentsapp.model

data class Student(
    var name: String,
    var id: String,
    val avatarUrl: String = "",
    var isChecked: Boolean,
    var phone: String,
    var address: String
)
