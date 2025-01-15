package com.example.firstapplication.model

class Model private constructor(){
    val students:MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..5){
            val student = Student(
                name = "Student $i",
                id = "Student id: $i",
                address = "Tel-Aviv",
                phone = "054-5122011",
                isChecked = false,
            )
            students.add(student);
        }
    }
}