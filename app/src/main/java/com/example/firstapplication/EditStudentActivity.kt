package com.example.firstapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapplication.model.Model
import com.example.firstapplication.model.Student


class EditStudentActivity : AppCompatActivity() {
    private val DEFAULT_STUDENT_POSITION = 0
    private var studentPosition: Int = DEFAULT_STUDENT_POSITION

    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var isCheckedCheckBox: CheckBox? = null


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

        studentPosition = intent.getIntExtra("student_position", DEFAULT_STUDENT_POSITION)
        val student = Model.shared.students[studentPosition]
        setStudentData(student)
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
            isChecked = isCheckedCheckBox?.isChecked ?: false
        )
    }

    private fun setListenersToButtons() {
        findViewById<Button>(R.id.edit_student_activity_save_button).apply {
            setOnClickListener {
                val student = createStudentFromInputs()
                Model.shared.students[studentPosition] = student
                finish()
            }
        }

        findViewById<Button>(R.id.edit_student_activity_delete_button).apply {
            setOnClickListener {
                Model.shared.students.removeAt(studentPosition)
                finish()
            }
        }

        findViewById<Button>(R.id.edit_student_activity_cancel_button).apply {
            setOnClickListener {
                finish()
            }
        }
    }
}
