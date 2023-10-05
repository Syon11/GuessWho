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
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
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
        drawCharacters()
        setSpinner();

    }

    private fun setSpinner(){
        spinner = findViewById(R.id.spinner_questions)
        adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
    }

    private fun drawCharacters() {
        var done = false
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val primaryLinearLayout: LinearLayout = findViewById(R.id.primaryLinearLayout)

        for (i in 0..(numberOfCharacters/3)+1 ) {
            var relLayout = RelativeLayout(this)
            var layout = LinearLayout(this)
            var layoutOverlay = LinearLayout(this)
            layout.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels,180)
            primaryLinearLayout.addView(layout)
            for (j in 0..2) {
                if (j+(i*3) >= numberOfCharacters){
                    done = true;
                    break;
                }
                createCard(layout, j+(i*3))
            }
            if (done){
                break;
            }
        }
    }
    private fun toggleButton(button: Button){
        button.isEnabled = !button.isEnabled
    }
    private fun createTextView(width: Int, height: Int, character: Character): TextView{
        var textView = TextView(this)
        val textParams = LinearLayout.LayoutParams( width, height)
        textParams.setMargins(30,0,5,0)
        textParams.gravity = Gravity.CENTER
        textView.layoutParams = textParams
        textView.text = character.getName()
        return textView
    }

    private fun createImageView(width: Int, height: Int, character: Character): ImageView{
        var imageView = ImageView(this)
        val imageParams = LinearLayout.LayoutParams(width, height)
        imageParams.setMargins(0, 0, 0, 0)
        imageParams.gravity = Gravity.CENTER
        imageView.layoutParams = imageParams
        imageView.setImageResource(character.getDrawableLocation())
        return imageView
    }

    private fun createCard(parent: LinearLayout, index: Int): LinearLayout{
        var verticalLayout = LinearLayout(this)
        verticalLayout.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        verticalLayout.orientation = LinearLayout.VERTICAL
        verticalLayout.gravity = Gravity.CENTER
        parent.addView(verticalLayout)
        var character: Character = Singleton.getCharacter(index)

        verticalLayout.addView(createImageView(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, character));
        verticalLayout.addView(createTextView(300, ViewGroup.LayoutParams.WRAP_CONTENT, character))

    }
}