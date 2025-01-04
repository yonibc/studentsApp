package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class StudentsRecyclerViewActivity : AppCompatActivity() {

    private lateinit var adapter: StudentsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_recycler_view)

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentsRecyclerAdapter(Model.shared.students) { position ->
            val intent = Intent(this, StudentDetailsActivity::class.java).apply {
                putExtra("studentIndex", position)
            }
            startActivity(intent)
        }

        recyclerView.adapter = adapter

        findViewById<View>(R.id.add_student_button).setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    class StudentsRecyclerAdapter(
        private val students: List<Student>,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.Adapter<StudentsRecyclerAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            private val nameTextView: TextView = view.findViewById(R.id.student_name)
            private val idTextView: TextView = view.findViewById(R.id.student_id)
            private val checkBox: CheckBox = view.findViewById(R.id.student_check)
            private val avatar: ImageView = view.findViewById(R.id.student_avatar)

            fun bind(student: Student, position: Int) {
                nameTextView.text = student.name
                idTextView.text = student.id
                checkBox.isChecked = student.isChecked
                avatar.setImageResource(R.drawable.profile_avatar)

                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    student.isChecked = isChecked
                }

                itemView.setOnClickListener {
                    onItemClick(position)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.student_list_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(students[position], position)
        }

        override fun getItemCount() = students.size
    }
}
