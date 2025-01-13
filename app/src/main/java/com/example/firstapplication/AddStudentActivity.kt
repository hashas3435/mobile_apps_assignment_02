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

class AddStudentActivity : AppCompatActivity() {
    private var nameEditText: EditText? = null
    private var idEditText: EditText? = null
    private var phoneEditText: EditText? = null
    private var addressEditText: EditText? = null
    private var isCheckedCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameEditText = findViewById<EditText>(R.id.nameEditText)
        idEditText = findViewById<EditText>(R.id.idEditText)
        phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        addressEditText = findViewById<EditText>(R.id.addressEditText)
        isCheckedCheckBox = findViewById<CheckBox>(R.id.isCheckedCheckBox)
        setListenersToButtons()
    }

    private fun createStudentFromInputs(): Student {
        return Student(
            id = nameEditText?.text.toString(),
            name = idEditText?.text.toString(),
            phone = phoneEditText?.text.toString(),
            address = addressEditText?.text.toString(),
            isChecked = isCheckedCheckBox?.isChecked ?: false
        )
    }


    private fun setListenersToButtons() {
        val createUserButton = findViewById<Button>(R.id.createButton)
        createUserButton.setOnClickListener {
            val student = createStudentFromInputs()
            Model.shared.students.add(student)
        }

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            finish()
        }
    }

}