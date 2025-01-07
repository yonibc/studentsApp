package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class EditStudentActivity : AppCompatActivity() {
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Get the student index passed from the details screen
        studentIndex = intent.getIntExtra("studentIndex", -1)
        if (studentIndex == -1) {
            finish()
            return
        }
        val student = Model.shared.students[studentIndex]

        // Bind data to views
        val nameEditText = findViewById<EditText>(R.id.name_input)
        val idEditText = findViewById<EditText>(R.id.id_input)
        val phoneEditText = findViewById<EditText>(R.id.phone_input)
        val addressEditText = findViewById<EditText>(R.id.address_input)
        val isCheckedCheckbox = findViewById<CheckBox>(R.id.checked_input)

        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        isCheckedCheckbox.isChecked = student.isChecked

        // Save button updates the student details
        findViewById<Button>(R.id.save_button).setOnClickListener {
            student.name = nameEditText.text.toString()
            student.id = idEditText.text.toString()
            student.phone = phoneEditText.text.toString()
            student.address = addressEditText.text.toString()
            student.isChecked = isCheckedCheckbox.isChecked

            setResult(RESULT_OK)
            finish()
        }

        // Cancel button discards changes
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            finish()
        }
    }
}
