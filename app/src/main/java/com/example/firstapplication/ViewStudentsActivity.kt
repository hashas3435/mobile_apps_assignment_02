package com.example.firstapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.adapter.OnItemClickListener
import com.example.firstapplication.adapter.StudentsRecyclerAdapter
import com.example.firstapplication.model.Model
import com.example.firstapplication.model.Student

class ViewStudentsActivity : AppCompatActivity() {
    private var students: MutableList<Student>? = null
    private var adapter:  StudentsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_students)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students = Model.shared.students
        val recyclerView: RecyclerView = findViewById(R.id.view_students_activity)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        this.adapter = createAdapter(Model.shared.students)
        recyclerView.adapter = this.adapter

        findViewById<Button>(R.id.add_student_button).apply {
            setOnClickListener {
                val intent = Intent(this@ViewStudentsActivity, AddStudentActivity::class.java)
                startActivity(intent)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        this.adapter?.update(Model.shared.students)
        this.adapter?.notifyDataSetChanged()
    }

    private fun createAdapter(students: MutableList<Student>): StudentsRecyclerAdapter{
        val initAdapter = StudentsRecyclerAdapter(students)

        initAdapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int){
                val intent = Intent(this@ViewStudentsActivity, StudentDetailsActivity::class.java)
                intent.putExtra("student_position", position)
                startActivity(intent)
            }
            override fun onItemClick(student: Student?){
                val intent = Intent(this@ViewStudentsActivity, StudentDetailsActivity::class.java)
                val position = Model.shared.students.indexOf(student)
                intent.putExtra("student_position", position)
                startActivity(intent)
            }
        }
        return initAdapter
    }

}