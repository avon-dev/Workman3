package com.example.workman.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workman.Model.GroupCalendar
import com.example.workman.R
import java.lang.RuntimeException

class GroupCalendarAdapter(private val context: Context, private val list: ArrayList<GroupCalendar>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View?
        return when (viewType) {
            1 -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_view_group_calendar1,parent,false)
                ViewHolder1(view)
            }
            2 -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_view_group_calendar2,parent,false)
                ViewHolder2(view)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = list[position]
        when(obj.type) {
            1 -> {
                (holder as ViewHolder1)
            }
            2 -> {
                (holder as ViewHolder2)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    // 7개일때의 평소타입
    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var Group_Name : TextView
        var Group1 : TextView
        var Group2 : TextView
        var Group3 : TextView
        var Group4 : TextView
        var Group5 : TextView
        var Group6 : TextView
        var Group7 : TextView

        init {
            Group_Name = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView_name)
            Group1 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView1)
            Group2 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView2)
            Group3 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView3)
            Group4 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView4)
            Group5 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView5)
            Group6 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView6)
            Group7 = itemView.findViewById(R.id.ItemView_Group_Calendar1_TextView7)
        }
        override fun onClick(v: View?) {
        }

    }

    // 31일 일때(3개)
    class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var day_of_week : TextView
        var day : TextView

        init {
            day_of_week = itemView.findViewById(R.id.ItemViewCalendar1_TextView2)
            day = itemView.findViewById(R.id.ItemViewCalendar1_TextView1)
        }
        override fun onClick(v: View?) {
        }

    }

}