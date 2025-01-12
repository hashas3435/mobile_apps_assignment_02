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

class AddStudentActivity : AppCompatActivity() {
    var nameEditText: EditText? = null
    var idEditText: EditText? = null
    var phoneEditText: EditText? = null
    var addressEditText: EditText? = null
    var isCheckedCheckBox: CheckBox? = null

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

    private fun setListenersToButtons() {


        val createUserButton = findViewById<Button>(R.id.createButton)
        createUserButton.setOnClickListener {
            // TODO: add student to students model
            Log.i("User", "name: ${nameEditText?.text}, id: ${idEditText?.text}")
        }

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        cancelButton.setOnClickListener {
            finish()
        }
    }

}