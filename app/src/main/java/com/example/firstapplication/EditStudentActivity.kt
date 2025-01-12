package com.example.firstapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapplication.model.Student

const val DEFAULT_STUDENT_POSITION = 0

class EditStudentActivity : AppCompatActivity() {
    var student: Student? = null

    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var phoneEditText: EditText? = null
    var addressEditText: EditText? = null
    var isCheckedCheckBox: CheckBox? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameEditText = findViewById(R.id.edit_student_activity_name_editText)
        idEditText = findViewById(R.id.edit_student_activity_id_editText)
        phoneEditText = findViewById(R.id.edit_student_phone_editText)
        addressEditText = findViewById(R.id.edit_student_activity_address_editText)
        isCheckedCheckBox = findViewById(R.id.edit_student_activity_checked_checkBox)

        val studentPosition = intent.getIntExtra("student_position", DEFAULT_STUDENT_POSITION)
        val students = mutableListOf(
            Student(
                name = "tomer shomron",
                id = "someId",
                phone = "04325234",
                address = "Gan-yavne",
                isChecked = true
            ),
            Student(
                name = "omer hasid",
                id = "someId2",
                phone = "3454676375",
                address = "Idk",
                isChecked = false
            )
        )
        student = students[studentPosition]

        student?.let {
            setStudentData(it)
        }
        setListenersToButtons()
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
            isChecked = isCheckedCheckBox?.isChecked ?: student?.isChecked ?: false
        )
    }

    private fun setListenersToButtons() {
        val saveStudentButton = findViewById<Button>(R.id.edit_student_activity_save_button)
        saveStudentButton.setOnClickListener {
            // TODO: update student to students model
            val student = createStudentFromInputs()
            Log.i("User", "update student: $student")
        }

        val deleteStudentButton = findViewById<Button>(R.id.edit_student_activity_delete_button)
        deleteStudentButton.setOnClickListener {
            // TODO: delete student from students model
            val student = createStudentFromInputs()
            Log.i("User", "delete student: $student")
        }

        val cancelButton = findViewById<Button>(R.id.edit_student_activity_cancel_button)
        cancelButton.setOnClickListener {
            finish()
        }
    }
}
