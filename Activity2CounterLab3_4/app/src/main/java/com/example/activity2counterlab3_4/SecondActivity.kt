package com.example.activity2counterlab3_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        counterTextView = findViewById(R.id.counterTextView)
        backButton = findViewById(R.id.backButton)

        // Отримуємо значення лічильника з Intent
        val counter = intent.getIntExtra("counter", 0)
        counterTextView.text = counter.toString()

        backButton.setOnClickListener {
            // Повертаємо значення лічильника назад до MainActivity
            val intent = Intent().apply {
                putExtra("counter", counter) // передаємо значення лічильника
            }
            setResult(RESULT_OK, intent) // Встановлюємо результат
            finish() // Закриваємо SecondActivity
        }
    }
}