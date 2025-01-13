package com.example.firstapplication.model

class Model private constructor(){
    val students:MutableList<Student> = ArrayList()
    companion object {
        val shared = Model()
    }

    init {
        for (i in 0..20){
            val student = Student(
                name = "Student $i",
                id = "Student id: $i",
                phone = "phone $i",
                address = "address $i",
                isChecked = false,
            )
            students.add(student);
        }
    }
}