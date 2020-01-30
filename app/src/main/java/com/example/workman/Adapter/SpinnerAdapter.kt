package com.example.workman.Adapter

import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.R
import android.annotation.SuppressLint
import android.widget.TextView
import android.content.Context.LAYOUT_INFLATER_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.view.LayoutInflater
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.spinner.view.*


class SpinnerAdapter constructor(var context: Activity,var groupid: Int,var list:ArrayList<ItemData>) :ArrayAdapter<ItemData>(context,groupid,list){
    private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = inflater.inflate(groupid, parent, false)
        val spinnerView = itemView.spinnercolor as TextView
        spinnerView.setBackgroundResource(list[position].getcolorId())
        spinnerView.text = list[position].getcolorname()

        return itemView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }
}