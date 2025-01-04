package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // Get the student index
        studentIndex = intent.getIntExtra("studentIndex", -1)
        if (studentIndex == -1) {
            finish()
            return
        }

        val student = Model.shared.students[studentIndex]

        // Bind data to views
        findViewById<TextView>(R.id.name_detail).text = student.name
        findViewById<TextView>(R.id.id_detail).text = student.id
        findViewById<TextView>(R.id.phone_detail).text = student.phone
        findViewById<TextView>(R.id.address_detail).text = student.address
        findViewById<CheckBox>(R.id.checked_detail).isChecked = student.isChecked

        // Edit button functionality
        findViewById<Button>(R.id.edit_button).setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("studentIndex", studentIndex)
            startActivity(intent)
        }
    }
}