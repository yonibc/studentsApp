package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class EditStudentActivity : AppCompatActivity() {
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        // Get the student index passed from the details screen
        studentIndex = intent.getIntExtra("studentIndex", -1)
        val student = Model.shared.students[studentIndex]

        // Bind data to views
        val nameEditText = findViewById<EditText>(R.id.name_input)
        val phoneEditText = findViewById<EditText>(R.id.phone_input)
        val addressEditText = findViewById<EditText>(R.id.address_input)
        val isCheckedCheckbox = findViewById<CheckBox>(R.id.checked_input)
        val idTextView = findViewById<TextView>(R.id.id_display)

        nameEditText.setText(student.name)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)
        isCheckedCheckbox.isChecked = student.isChecked
        idTextView.text = student.id // Display ID as non-editable

        // Save button updates the student details
        findViewById<Button>(R.id.save_button).setOnClickListener {
            student.name = nameEditText.text.toString()
            student.phone = phoneEditText.text.toString()
            student.address = addressEditText.text.toString()
            student.isChecked = isCheckedCheckbox.isChecked

            // Pass result back to the details activity
            setResult(RESULT_OK)
            finish()
        }

        // Cancel button discards changes
        findViewById<Button>(R.id.cancel_button).setOnClickListener {
            finish() // Close the activity without saving
        }
    }
}
