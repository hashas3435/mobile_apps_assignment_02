package com.example.firstapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.firstapplication.model.Model
import com.example.firstapplication.model.Student


class EditStudentFragment : Fragment() {
    private var studentPosition: Int = 0

    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var isCheckedCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        studentPosition =
            arguments?.let { EditStudentFragmentArgs.fromBundle(it).studentPosition } ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        nameEditText = view.findViewById(R.id.edit_student_fragment_name_editText)
        idEditText = view.findViewById(R.id.edit_student_fragment_id_editText)
        phoneEditText = view.findViewById(R.id.edit_student_phone_editText)
        addressEditText = view.findViewById(R.id.edit_student_fragment_address_editText)
        isCheckedCheckBox = view.findViewById(R.id.edit_student_fragment_checked_checkBox)

        val student = Model.shared.students[studentPosition]
        setStudentData(student)
        setListenersToButtons(view)

        return view
    }

    private fun setStudentData(student: Student) {
        nameEditText?.setText(student.name)
        idEditText?.setText(student.id)
        phoneEditText?.setText(student.phone)
        addressEditText?.setText(student.address)
        isCheckedCheckBox?.isChecked = student.isChecked
    }

    private fun createStudentFromInputs(): Student {
        return Student(
            name = nameEditText?.text.toString(),
            id = idEditText?.text.toString(),
            phone = phoneEditText?.text.toString(),
            address = addressEditText?.text.toString(),
            isChecked = isCheckedCheckBox?.isChecked ?: false
        )
    }

    private fun setListenersToButtons(view: View) {
        view.findViewById<Button>(R.id.edit_student_fragment_save_button).apply {
            setOnClickListener {
                val student = createStudentFromInputs()
                Model.shared.students[studentPosition] = student
                Navigation.findNavController(view).popBackStack()
            }
        }

        view.findViewById<Button>(R.id.edit_student_fragment_delete_button).apply {
            setOnClickListener {
                Model.shared.students.removeAt(studentPosition)
                Navigation.findNavController(view).popBackStack()
            }
        }

        view.findViewById<Button>(R.id.edit_student_fragment_cancel_button).apply {
            setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }

}