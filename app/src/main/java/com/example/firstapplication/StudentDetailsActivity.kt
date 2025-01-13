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
import com.example.firstapplication.model.Model
import com.example.firstapplication.model.Student

const val DEFAULT_STUDENT_POSITION = 0

class StudentDetailsActivity : AppCompatActivity() {
    private var studentPosition: Int = DEFAULT_STUDENT_POSITION

    private var nameEditText: TextView? = null
    private var idEditText: TextView? = null
    private var phoneEditText: TextView? = null
    private var addressEditText: TextView? = null
    private var isCheckedCheckBox: CheckBox? = null

    private var saveStudentButton: Button? = null

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
        setStudentData(Model.shared.students[studentPosition])
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