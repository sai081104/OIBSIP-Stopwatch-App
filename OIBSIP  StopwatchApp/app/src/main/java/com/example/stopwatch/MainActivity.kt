
package com.example.stopwatch

import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var startBtn: Button
    private lateinit var stopBtn: Button
    private lateinit var resetBtn: Button

    private var seconds = 0
    private var running = false
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText = findViewById(R.id.timerText)
        startBtn = findViewById(R.id.startBtn)
        stopBtn = findViewById(R.id.stopBtn)
        resetBtn = findViewById(R.id.resetBtn)

        startBtn.setOnClickListener {
            running = true
        }

        stopBtn.setOnClickListener {
            running = false
        }

        resetBtn.setOnClickListener {
            running = false
            seconds = 0
            timerText.text = "0:00"
        }

        runTimer()
    }

    private fun runTimer() {
        handler.post(object : Runnable {
            override fun run() {
                val minutes = seconds / 60
                val secs = seconds % 60
                timerText.text = String.format("%d:%02d", minutes, secs)
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }
}
