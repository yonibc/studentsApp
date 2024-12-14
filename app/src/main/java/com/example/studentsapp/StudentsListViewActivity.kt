package com.example.studentsapp

import android.os.Bundle
import android.util.Log
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

    var students: MutableList<Student>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.shared.students
        val listView: ListView = findViewById(R.id.students_list_view)
        listView.adapter = StudentsAdapter()
    }

    inner class StudentsAdapter(): BaseAdapter() {
        override fun getCount(): Int = students?.size ?: 2 // if students is empty, create 2 rows

        override fun getItem(p0: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getItemId(p0: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
            val inflator = LayoutInflater.from(parent?.context)
            val view = converView ?: inflator.inflate(
                R.layout.student_list_row,
                parent,
                false
            ).apply {
                findViewById<CheckBox>(R.id.student_row_check_box).apply {
                    setOnClickListener { view ->
                        (tag as? Int)?.let { tag ->
                            val student = students?.get(tag)
                            student?.isChecked = (view as? CheckBox)?.isChecked ?: false
                        }
                    }
                }
            }


//            var view = converView
//
//            if (view == null) {
//                view = inflator.inflate(R.layout.student_list_row, parent, false)
//                val checkBox: CheckBox? = view?.findViewById(R.id.student_row_check_box)
////                checkBox?.setOnClickListener {
////                    student?.isChecked = checkBox.isChecked
////                }
//
//                checkBox?.apply {
////                    setOnClickListener {
////                        (tag as? Int)?.let { tag ->
////                            val student = students?.get(tag)
////                            student?.isChecked = (view as? CheckBox)?.isChecked ?: false
////                        }
////                    }
//                }
//            }

            val student = students?.get(position)

            val nameTextView: TextView? = view?.findViewById(R.id.student_row_name_text_view)
            val idTextView: TextView? = view?.findViewById(R.id.student_row_id_text_view)
            val checkBox: CheckBox? = view?.findViewById(R.id.student_row_check_box)



            Log.d("TAG", "Inflating position $position")

            nameTextView?.text = student?.name
            idTextView?.text = student?.id
            checkBox?.apply {
                isChecked = student?.isChecked ?: false
                tag = position
            }
//            checkBox?.isChecked = student?.isChecked ?: false



            // if null, it crashes. we use it in rare cases, like here (we check before if view is null)
            return view!!
        }

    }
}