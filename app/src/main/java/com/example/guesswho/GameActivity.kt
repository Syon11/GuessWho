package com.example.guesswho

import android.graphics.Point
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import com.example.guesswho.constants.Character
import com.example.guesswho.constants.Singleton

class GameActivity : AppCompatActivity() {


    var listOfDrawables = mutableListOf<Drawable>()

    var numberOfCharacters = Singleton.getNumberOfCharacters()
    var numberOfQuestions = Singleton.getNumberOfQuestions()

    lateinit var spinner: Spinner
    lateinit var adapter: ArrayAdapter<CharSequence>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        var done = false
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val primaryLinearLayout: LinearLayout = findViewById(R.id.primaryLinearLayout)

        for (i in 0..(numberOfCharacters/3)*2 step 2 ) {
            var layout = LinearLayout(this)
            layout.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels,150)
            primaryLinearLayout.addView(layout)
            for (j in 0..2){
                if (j+((i/2)*3) == numberOfCharacters){
                    done = true;
                    break;
                }
                var imageView = ImageView(this)
                var character: Character = Singleton.getCharacter(j+((i/2)*3))
                val imageParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                imageParams.setMargins(30, 10, 0, 5)
                imageParams.gravity = Gravity.CENTER
                imageView.layoutParams = imageParams
                imageView.setImageResource(character.getDrawableLocation())
                layout.addView(imageView);
            }
            if (done){
                break
            }
            var layout2 = LinearLayout(this)
            layout2.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels, 500)
            primaryLinearLayout.addView(layout2)
            for (j in 0..2){
                var textView = TextView(this)
                var character = Singleton.getCharacter(j+((i/2)*3))
                val textParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                textParams.setMargins(50,0,0,0)
                textParams.gravity = Gravity.CENTER
                textView.layoutParams = textParams
                textView.text = character.getName()
                layout.addView(textView)
            }
        }
        setSpinner();
    }

    private fun setSpinner(){
        spinner = findViewById(R.id.spinner_questions)
        adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
    }
}