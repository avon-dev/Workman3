package com.example.workman.Adapter

class ItemData constructor(private var colorid:Int, var colorname:String){

    fun getcolorId(): Int {
        return colorid
    }

    fun getcolorname(): String {
        return colorname
    }
}