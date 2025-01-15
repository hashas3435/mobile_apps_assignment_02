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

class AddStudentFragment : Fragment() {
    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var isCheckedCheckBox: CheckBox? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        nameEditText = view.findViewById(R.id.add_student_fragment_name_editText)
        idEditText = view.findViewById(R.id.add_student_fragment_id_editText)
        phoneEditText = view.findViewById(R.id.add_student_fragment_phone_editText)
        addressEditText = view.findViewById(R.id.add_student_fragment_address_editText)
        isCheckedCheckBox = view.findViewById(R.id.add_student_fragment_isChecked_checkBox)
        setListenersToButtons(view)

        return view
    }

    private fun createStudentFromInputs(): Student {
        return Student(
            id = idEditText?.text.toString(),
            name = nameEditText?.text.toString(),
            phone = phoneEditText?.text.toString(),
            address = addressEditText?.text.toString(),
            isChecked = isCheckedCheckBox?.isChecked ?: false
        )
    }


    private fun setListenersToButtons(view: View) {
        view.findViewById<Button>(R.id.add_student_fragment_create_button).apply {
            setOnClickListener {
                val student = createStudentFromInputs()
                Model.shared.students.add(student)
                Navigation.findNavController(view).popBackStack()
            }
        }

        view.findViewById<Button>(R.id.add_student_fragment_cancel_button).apply {
            setOnClickListener {
                Navigation.findNavController(view).popBackStack()
            }
        }
    }
}