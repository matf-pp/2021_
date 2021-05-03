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
        lista=helper.getAllOnDay()

        dayAdapter=DayAdapter(helper,lista)

        dayActivity.rvAktivnosti.adapter=dayAdapter
        dayActivity.rvAktivnosti.layoutManager=LinearLayoutManager(this)


        dayActivity.btnBack.setOnClickListener {
            val intent = Intent(this,Schedule::class.java)
            startActivity(intent)
        }

        dayActivity.dayAdd.setOnClickListener {
            val aktivnost=dayActivity.etAkivnost.text.toString()
            val vreme=dayActivity.etVreme.text.toString()
            if(aktivnost.isNotEmpty() && vreme.isNotEmpty()){
                helper.insertDay(aktivnost,vreme)
                lista=helper.getAllOnDay()
                dayActivity.etAkivnost.setText("")
                dayActivity.etVreme.setText("")
                dayAdapter.setActivities()
            }
        }


    }


}