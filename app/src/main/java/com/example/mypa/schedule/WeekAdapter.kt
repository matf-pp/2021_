package com.example.mypa.schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mypa.MyDBHelper
import com.example.mypa.R

class WeekAdapter(private var myDB: MyDBHelper, private val context: Context, private var days: Array<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return days.size
    }

    override fun getItem(position: Int): Any {
        return days[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val oneDay = LayoutInflater.from(context).inflate(R.layout.onedayitem,parent,false)

        val current: String = days[position]

        val tvDayName : TextView = oneDay.findViewById(R.id.tvDays)
        tvDayName.setText(current)

        return oneDay
    }
}