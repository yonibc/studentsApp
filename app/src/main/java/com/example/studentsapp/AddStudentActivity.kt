package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_activity_cancel_button)

        val savedMessage: TextView = findViewById(R.id.add_student_activity_saved_message_text_view)

        val nameEditText: EditText = findViewById(R.id.add_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.add_student_activity_id_edit_text)
        val phoneEditText: EditText = findViewById(R.id.add_student_activity_phone_edit_text)
        val addressEditText: EditText = findViewById(R.id.add_student_activity_address_edit_text)
        val isCheckBox: CheckBox = findViewById(R.id.add_student_activity_is_checked_check_box)

        cancelButton.setOnClickListener { finish() }

        saveButton.setOnClickListener {
            // Create an Intent to pass the data back
            val intent = Intent().apply {
                putExtra("name", nameEditText.text.toString())
                putExtra("id", idEditText.text.toString())
                putExtra("phone", phoneEditText.text.toString())
                putExtra("address", addressEditText.text.toString())
                putExtra("isChecked", isCheckBox.isChecked)
            }
            setResult(RESULT_OK, intent) // Send the result back
            finish() // Close this activity
        }
    }
}
