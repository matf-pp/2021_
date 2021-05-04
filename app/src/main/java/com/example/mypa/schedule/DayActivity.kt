package com.example.mypa.schedule

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mypa.MyDBHelper
import com.example.mypa.databinding.ActivityDayBinding

class DayActivity : AppCompatActivity() {

    private lateinit var dayActivity : ActivityDayBinding
    private lateinit var dayAdapter: DayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dayActivity= ActivityDayBinding.inflate(layoutInflater)
        setContentView(dayActivity.root)

        lateinit var lista:MutableList<Day>
        val helper = MyDBHelper(applicationContext)
        lista=helper.getAllOnDay()

        dayAdapter= DayAdapter(helper,lista)

        dayActivity.rvAktivnosti.adapter=dayAdapter
        dayActivity.rvAktivnosti.layoutManager= LinearLayoutManager(this)

        dayActivity.btnBack.setOnClickListener {
            val intent = Intent(this, Schedule::class.java)
            startActivity(intent)
        }

        dayActivity.dayAdd.setOnClickListener {
            val aktivnost=dayActivity.etAkivnost.text.toString()
            val vreme=dayActivity.etVreme.text.toString()

            val timeRegex = Regex("([01][0-9]|2[0-3]):([0-5][0-9])")
            val matchtime = timeRegex.matchEntire(vreme)

            if(matchtime==null)
                dayActivity.etVreme.setText("")

            if(aktivnost.isNotEmpty() && vreme.isNotEmpty() && matchtime != null){
                helper.insertDay(aktivnost,vreme)
                lista=helper.getAllOnDay()
                dayActivity.etAkivnost.setText("")
                dayActivity.etVreme.setText("")
                dayAdapter.setActivities()
            }
        }
    }
}