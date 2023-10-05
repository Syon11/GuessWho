package com.example.guesswho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.view.View

class MainActivity : AppCompatActivity() {
    lateinit var playButton: Button
    lateinit var spinner: Spinner
    lateinit var adapter: ArrayAdapter<CharSequence>
    var diff = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDifficultySpinner()
        playButton = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("Difficulty", diff)
            startActivityForResult(intent, 2)
        }
    }


    private fun setDifficultySpinner(){
        spinner = findViewById(R.id.spinner_difficulty)
        adapter = ArrayAdapter.createFromResource(this, R.array.difficulties, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (spinner.selectedItem == "Easy") {
                    setDifficulty(0)
                } else if (spinner.selectedItem == "Normal") {
                    setDifficulty(1)
                } else {
                    setDifficulty(2)
                }
            }
        }
    }
    private fun setDifficulty(id: Int){
        diff = id
    }
}