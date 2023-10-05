package com.example.guesswho.constants

import android.graphics.drawable.Drawable

class Character (name: String, drawable: Int){
    private val name = name
    private var values = BooleanArray(9)
    private var drawableID = drawable;

    fun setQuestion(number: Int){
        values[number] = true;
    }

    fun getQuestion(number: Int): Boolean{
        return values[number]
    }

    fun getName(): String{
        return name
    }

    fun getDrawableLocation(): Int{
        return drawableID
    }
}