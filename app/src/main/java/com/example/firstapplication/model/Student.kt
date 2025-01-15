package com.example.firstapplication.model

data class Student(
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    var isChecked: Boolean
){
    override fun equals(other: Any?): Boolean =
        (other is Student) && this.id == other.id
}
