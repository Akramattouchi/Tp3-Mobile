package com.gl4.tp2_presenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp2mobile.Student
import com.gl4.tp2mobile.StudentsAdapter

class MainActivity : AppCompatActivity() {

    val recycler : RecyclerView by lazy { findViewById(R.id.recycler) }
    val tousRadio : RadioButton by lazy { findViewById(R.id.tousRadio) }
    val presentsRadio : RadioButton by lazy { findViewById(R.id.presentsRadio) }
    val absentsRadio : RadioButton by lazy { findViewById(R.id.absentsRadio) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tousRadio.isChecked = true

        var students = arrayListOf<Student>(
            Student(0,"Yahya", "Omar", "M", true),
            Student(1,"Radaoui", "Mariem", "F", true),
            Student(2,"Hajji", "Roua", "F", false),
            Student(3,"Gabsi", "Hmayda", "M", true),
            Student(4,"Elbir", "Walid", "M", false),

        )

        var studentsAdapter = StudentsAdapter(students)

        presentsRadio.setOnCheckedChangeListener{ view , value ->
            if(value == true){
                studentsAdapter.filter.filter(value.toString())
            }
        }

        absentsRadio.setOnCheckedChangeListener{ view , value ->
            if(value == true){
                studentsAdapter.filter.filter((!value).toString())
            }
        }

        tousRadio.setOnCheckedChangeListener{ view , value ->
            if(value == true){
                studentsAdapter.filter.filter("")
            }
        }

        recycler.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = studentsAdapter
        }
    }
}