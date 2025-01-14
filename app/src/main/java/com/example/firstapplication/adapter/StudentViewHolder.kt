package com.example.firstapplication.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapplication.R
import com.example.firstapplication.model.Student

interface OnItemClickListener {
    fun onItemClick(position: Int)
    fun onItemClick(student: Student?)
}

class StudentViewHolder(
    itemView: View,
    listener: OnItemClickListener?
): RecyclerView.ViewHolder(itemView) {

    private var nameTextView: TextView? = null
    private var idTextView: TextView? = null
    private var studentCheckBox: CheckBox? = null
    private var student: Student? = null

    init {
        nameTextView = itemView.findViewById(R.id.student_row_name_text_view)
        idTextView = itemView.findViewById(R.id.student_row_id_text_view)
        studentCheckBox = itemView.findViewById(R.id.student_row_check_box)

        studentCheckBox?.apply {
            setOnClickListener {
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                }
            }
        }

        itemView.setOnClickListener {
            listener?.onItemClick(student)
        }
    }

    fun bind(student: Student?, position: Int) {
        this.student = student
        nameTextView?.text = student?.name
        idTextView?.text = student?.id

        studentCheckBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position
        }
    }
}