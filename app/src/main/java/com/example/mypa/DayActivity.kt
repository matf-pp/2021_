package com.example.mypa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.databinding.ActivityDayBinding

class DayActivity : AppCompatActivity() {

    private lateinit var dayActivity : ActivityDayBinding
    private lateinit var dayAdapter: DayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dayActivity= ActivityDayBinding.inflate(layoutInflater)
        setContentView(dayActivity.root)

        lateinit var lista:MutableList<Day>
        var helper = MyDBHelper(applicationContext)

        //TODO namestiti da se prosledi broj koji je to dan u nedelji

    //    lista=helper.getAllOnDay(Integer(0))
        var pom =Day(Integer(1),Integer(1),"trening","11:00")
        lista = mutableListOf(pom)
        dayAdapter=DayAdapter(lista)

        dayActivity.rvAktivnosti.adapter=dayAdapter
        dayActivity.rvAktivnosti.layoutManager=LinearLayoutManager(this)


        dayActivity.btnBack.setOnClickListener {
            val intent = Intent(this,Schedule::class.java)
            startActivity(intent)
        }



    }


}