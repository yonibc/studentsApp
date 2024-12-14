package com.example.studentsapp.model


class Model private constructor(){

    val students: MutableList<Student> = ArrayList()

    // singleton
    companion object {
        val shared = Model()
    }

    init {
        for(i in 0..20) {
            val student = Student(
                name = "Name: $i",
                id = "Student ID: $i",
                avatarUrl = "",
                isChecked = false
            )
            students.add(student)
        }
    }
}