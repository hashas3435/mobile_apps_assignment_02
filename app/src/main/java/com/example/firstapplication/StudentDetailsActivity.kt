package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapplication.model.Student

const val DEFAULT_STUDENT_POSITION = 0

class StudentDetailsActivity : AppCompatActivity() {
    var studentPosition: Int = DEFAULT_STUDENT_POSITION

    var nameEditText: TextView? = null
    var idEditText: TextView? = null
    var phoneEditText: TextView? = null
    var addressEditText: TextView? = null
    var isCheckedCheckBox: CheckBox? = null

    var saveStudentButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameEditText = findViewById(R.id.edit_student_activity_name_textView)
        idEditText = findViewById(R.id.edit_student_activity_id_textView)
        phoneEditText = findViewById(R.id.edit_student_activity_phone_textView)
        addressEditText = findViewById(R.id.edit_student_activity_address_textView)
        isCheckedCheckBox = findViewById(R.id.edit_student_activity_checked_checkBox)

        studentPosition = intent.getIntExtra("student_position", DEFAULT_STUDENT_POSITION)
        // TODO: use students model
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
        setStudentData(students[studentPosition])
        setListenersToButtons()
    }

    private fun setStudentData(student: Student) {
        nameEditText?.text = student.name
        idEditText?.text = student.id
        phoneEditText?.text = student.phone
        addressEditText?.text = student.address
        isCheckedCheckBox?.isChecked = student.isChecked
    }

    private fun setListenersToButtons() {
        saveStudentButton = findViewById<Button>(R.id.student_details_activity_edit_button)
        saveStudentButton?.apply {
            setOnClickListener {
                val intent = Intent(this@StudentDetailsActivity, EditStudentActivity::class.java)
                intent.putExtra("student_position", studentPosition)
                startActivity(intent)
                finish()
            }
        }
    }
}