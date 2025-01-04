package com.example.studentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsListViewActivity : AppCompatActivity() {

    private var students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize students list
        students = Model.shared.students

        // Set up the ListView and adapter
        val listView: ListView = findViewById(R.id.students_list_view)
        listView.adapter = StudentsAdapter()
    }

    inner class StudentsAdapter : BaseAdapter() {
        override fun getCount(): Int = students?.size ?: 0

        override fun getItem(position: Int): Any = students?.get(position) ?: Student("", "", "", false, "", "")

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(parent?.context)
            val view = convertView ?: inflater.inflate(R.layout.student_list_row, parent, false)

            val student = students?.get(position)

            // Bind data to views
            val nameTextView: TextView = view.findViewById(R.id.student_name)
            val idTextView: TextView = view.findViewById(R.id.student_id)
            val checkBox: CheckBox = view.findViewById(R.id.student_check)

            nameTextView.text = student?.name ?: ""
            idTextView.text = student?.id ?: ""
            checkBox.isChecked = student?.isChecked ?: false

            // Handle CheckBox changes
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student?.isChecked = isChecked
            }

            return view
        }
    }
}
