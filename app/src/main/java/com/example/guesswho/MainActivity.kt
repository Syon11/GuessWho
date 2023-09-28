package com.example.guesswho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var playButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton = findViewById(R.id.playButton)

        playButton.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivityForResult(intent, 2)
        }
    }
}