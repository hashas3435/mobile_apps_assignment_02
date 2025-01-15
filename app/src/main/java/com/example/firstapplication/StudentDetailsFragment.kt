package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.firstapplication.model.Model
import com.example.firstapplication.model.Student


class StudentDetailsFragment : Fragment() {
    private var studentPosition: Int = 0

    private var nameEditText: TextView? = null
    private var idEditText: TextView? = null
    private var phoneEditText: TextView? = null
    private var addressEditText: TextView? = null
    private var isCheckedCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentPosition =
            arguments?.let { StudentDetailsFragmentArgs.fromBundle(it).studentPosition } ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_details, container, false)
        nameEditText = view.findViewById(R.id.student_details_fragment_name_textView)
        idEditText = view.findViewById(R.id.student_details_fragment_id_textView)
        phoneEditText = view.findViewById(R.id.student_details_fragment_phone_textView)
        addressEditText = view.findViewById(R.id.student_details_fragment_address_textView)
        isCheckedCheckBox = view.findViewById(R.id.student_details_fragment_checked_checkBox)

        setStudentData(Model.shared.students[studentPosition])
        setListenersToButtons(view)

        return view
    }

    private fun setStudentData(student: Student) {
        nameEditText?.text = student.name
        idEditText?.text = student.id
        phoneEditText?.text = student.phone
        addressEditText?.text = student.address
        isCheckedCheckBox?.isChecked = student.isChecked
    }

    private fun setListenersToButtons(view: View) {
        view.findViewById<Button>(R.id.student_details_fragment_edit_button).apply {
            setOnClickListener {
                val navController = Navigation.findNavController(view)
                navController.popBackStack()
                val intent = Intent(view.context, EditStudentActivity::class.java)
                intent.putExtra("student_position", studentPosition)
                startActivity(intent)
            }
        }
    }
}