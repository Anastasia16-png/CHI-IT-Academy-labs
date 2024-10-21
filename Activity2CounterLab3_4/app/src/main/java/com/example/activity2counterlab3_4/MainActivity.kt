package com.example.activity2counterlab3_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var navigateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.counterTextView)
        incrementButton = findViewById(R.id.incrementButton)
        navigateButton = findViewById(R.id.navigateButton)

        incrementButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()
            // Відправляємо значення лічильника в сервіс
            val intent = Intent(this, MyService::class.java).apply {
                putExtra("counter", counter)
            }
            startService(intent)
        }

        navigateButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("counter", counter)
            }
            startActivity(intent)
        }
    }
}