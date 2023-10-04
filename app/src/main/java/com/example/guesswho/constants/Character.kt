package com.example.guesswho.constants

class Character (name: String, drawableLocation: String){
    private val name = name
    private val drawableLocation = drawableLocation
    private var values = BooleanArray(9)

    fun setQuestion(number: Int){
        values[number] = true;
    }

    fun getQuestion(number: Int): Boolean{
        return values[number]
    }

    fun getName(): String{
        return name
    }

    fun getDrawableLocation(): String{
        return drawableLocation
    }
}