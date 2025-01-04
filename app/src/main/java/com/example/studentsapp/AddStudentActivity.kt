package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val saveButton: Button = findViewById(R.id.save_button)
        val cancelButton: Button = findViewById(R.id.cancel_button)

        val nameEditText: EditText = findViewById(R.id.name_input)
        val idEditText: EditText = findViewById(R.id.id_input)
        val phoneEditText: EditText = findViewById(R.id.phone_input)
        val addressEditText: EditText = findViewById(R.id.address_input)
        val checkBox: CheckBox = findViewById(R.id.checked_input)

        cancelButton.setOnClickListener { finish() }

        saveButton.setOnClickListener {
            val newStudent = Student(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString(),
                phone = phoneEditText.text.toString(),
                address = addressEditText.text.toString(),
                isChecked = checkBox.isChecked
            )

            Model.shared.students.add(newStudent)
            finish()
        }
    }
}
