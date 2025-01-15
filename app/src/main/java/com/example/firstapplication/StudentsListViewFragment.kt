package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.adapter.OnItemClickListener
import com.example.firstapplication.adapter.StudentsRecyclerAdapter
import com.example.firstapplication.model.Model
import com.example.firstapplication.model.Student

class StudentsListViewFragment : Fragment() {
    private var adapter: StudentsRecyclerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_students_list_view, container, false)
        val recyclerView: RecyclerView =
            view.findViewById(R.id.students_list_view_fragment_recyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        adapter = createAdapter(Model.shared.students)
        recyclerView.adapter = this.adapter

        setNavigateToAddStudentBtn(view)

        return view
    }

    private fun setNavigateToAddStudentBtn(view: View) {
        view.findViewById<Button>(R.id.students_list_view_fragment_add_student_button).apply {
            setOnClickListener {
                val intent = Intent(view.context, AddStudentActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList() {
        adapter?.update(Model.shared.students)
        adapter?.notifyDataSetChanged()
    }

    private fun navigateToStudentDetails(studentPosition: Int) {
        view?.let {
            val intent = Intent(it.context, StudentDetailsActivity::class.java)
            intent.putExtra("student_position", studentPosition)
            startActivity(intent)
        }
    }

    private fun createAdapter(students: MutableList<Student>): StudentsRecyclerAdapter {
        val initAdapter = StudentsRecyclerAdapter(students)

        initAdapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                navigateToStudentDetails(position)
            }


            override fun onItemClick(student: Student?) {
                val position = Model.shared.students.indexOf(student)
                navigateToStudentDetails(position)
            }
        }
        return initAdapter
    }
}