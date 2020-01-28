package com.example.workman

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TimeRvAdapter(val context: Context, private val timeList: ArrayList<Time>, val itemClick: (Time) -> Unit) : RecyclerView.Adapter<TimeRvAdapter.Holder>() {

    inner class Holder(itemView: View?, itemClick: (Time) -> Unit) : RecyclerView.ViewHolder(itemView!!) {
        val date = itemView?.findViewById<TextView>(R.id.textView18)
        val day = itemView?.findViewById<TextView>(R.id.textView19)
        val start = itemView?.findViewById<TextView>(R.id.textView20)
        val end = itemView?.findViewById<TextView>(R.id.textView23)
        val sum = itemView?.findViewById<TextView>(R.id.textView21)

        fun bind(time: Time, context: Context) {
            date?.text = time.date.toString()
            day?.text = time.day
            start?.text = time.startWork.toString()
            end?.text = time.endWork.toString()

            val i = time.endWork - time.startWork
            sum?.text = i.toString()

            itemView.setOnClickListener{itemClick(time)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.time_rv_item, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(timeList[position], context)
    }

}