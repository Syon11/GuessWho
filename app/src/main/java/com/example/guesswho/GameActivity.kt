package com.example.guesswho

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.TextView
import com.example.guesswho.constants.Character
import com.example.guesswho.constants.Singleton

class GameActivity : AppCompatActivity() {


    var numberOfCharacters = Singleton.getNumberOfCharacters()

    lateinit var spinner: Spinner
    lateinit var adapter: ArrayAdapter<CharSequence>
    lateinit var lblAttempts: TextView
    lateinit var confirmButton: Button
    lateinit var submitButton: Button
    var diff = 0
    var attempts = 0
    var selectedID = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diff = intent.getIntExtra("Difficulty", 0)
        initRemainingAttempts()
        setContentView(R.layout.game)
        lblAttempts = findViewById(R.id.idRemainingAttempts)
        lblAttempts.text = attempts.toString()
        confirmButton = findViewById(R.id.confirmButton)
        confirmButton.setOnClickListener {
            checkOffCharacters(0)
        }
        submitButton = findViewById(R.id.submitButton)
        submitButton.isEnabled = false
        submitButton.setOnClickListener {

        }
        initCharacters()
        setSpinner();

    }

    private fun setSpinner(){
        spinner = findViewById(R.id.spinner_questions)
        adapter = ArrayAdapter.createFromResource(this, R.array.questions, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (spinner.selectedItem) {
                    "Is the character a human?" -> selectedID = 0
                    "Is the character a Male?" -> selectedID = 1
                    "Is the character Good?" -> selectedID = 2
                    "Is the character wearing something on their head?" -> selectedID = 3
                    "Does the character have Hair?" -> selectedID = 4
                    "Does the character wear Clothes?" -> selectedID = 5
                    "Does the character talk?" -> selectedID = 6
                    "Can the character use supernatural abilities?" -> selectedID = 7
                    "Is the character high-tech?" -> selectedID = 8
                }
            }
        }
    }

    private fun initCharacters() {
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
    private fun activateButton(button: Button){
        button.isEnabled = true
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

    private fun createCard(parent: LinearLayout, index: Int){
        var verticalLayout = LinearLayout(this)
        verticalLayout.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        verticalLayout.orientation = LinearLayout.VERTICAL
        verticalLayout.gravity = Gravity.CENTER
        verticalLayout.id = index + 25565
        parent.addView(verticalLayout)
        verticalLayout.setOnClickListener {
            selectedID = index
            activateButton(submitButton)
        }
        var character: Character = Singleton.getCharacter(index)
        verticalLayout.addView(createImageView(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, character));
        verticalLayout.addView(createTextView(300, ViewGroup.LayoutParams.WRAP_CONTENT, character))

    }

    private fun initRemainingAttempts() {
        if (diff == 0) {
            attempts = 7
        } else if (diff == 1) {
            attempts = 5
        } else {
            attempts = 3
        }
    }

    private fun checkOffCharacters(questionID: Int) {
        attempts--
        if (attempts == 0) {
            confirmButton.isEnabled = false
        }
        for (i in 0..<Singleton.getNumberOfCharacters()) {

        }
    }

}