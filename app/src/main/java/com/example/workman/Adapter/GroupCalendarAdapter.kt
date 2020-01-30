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
            0 -> { // 1~4주차가 까지 7개일때.
                view = LayoutInflater.from(context).inflate(R.layout.item_view_group_calendar1,parent,false)
                ViewHolder0(view)
            }
            3 -> { // 5주차가 31일, 즉 3개일때.
                view = LayoutInflater.from(context).inflate(R.layout.item_view_group_calendar2,parent,false)
                ViewHolder3(view)
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
            0 -> {
                (holder as ViewHolder0)
            }
            3 -> {
                (holder as ViewHolder3)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    // 7개일때의 평소타입
    class ViewHolder0(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
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
    class ViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var Group_Name2 : TextView
        var Group2_1 : TextView
        var Group2_2 : TextView
        var Group2_3 : TextView

        init {
            Group_Name2 = itemView.findViewById(R.id.ItemView_Group_Calendar2_TextView_name)
            Group2_1 = itemView.findViewById(R.id.ItemView_Group_Calendar2_TextView1)
            Group2_2 = itemView.findViewById(R.id.ItemView_Group_Calendar2_TextView2)
            Group2_3 = itemView.findViewById(R.id.ItemView_Group_Calendar2_TextView3)
        }
        override fun onClick(v: View?) {
        }

    }

}