package com.example.mypa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class WeekAdapter(private val context: Context, private var days: Array<String>) : BaseAdapter() {

   // private var layoutInflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    /*class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val oneDayBinding = OnedayitemBinding.bind(itemView)
    }*/
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

        var oneDay = LayoutInflater.from(context).inflate(R.layout.onedayitem,parent,false)

        var current: String = days[position]

        var tvDayName : TextView = oneDay.findViewById(R.id.tvDays)
        tvDayName.setText(current)

        return oneDay

    }
}