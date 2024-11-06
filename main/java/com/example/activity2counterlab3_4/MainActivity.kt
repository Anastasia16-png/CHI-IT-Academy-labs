package com.example.activity2counterlab3_4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var counter = 0
    private lateinit var counterTextView: TextView
    private lateinit var incrementButton: Button
    private lateinit var navigateButton: Button
    private lateinit var sortButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        counterTextView = findViewById(R.id.counterTextView)
        incrementButton = findViewById(R.id.incrementButton)
        navigateButton = findViewById(R.id.navigateButton)
        sortButton = findViewById(R.id.sortButton)

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

        // обробник для кнопки сортування
        sortButton.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                val startTime = System.currentTimeMillis()

                val arr = intArrayOf(8, 4, 1, 6, 12, 10, 8, 7, 2)
                bubbleSort(arr) // запуск

                val endTime = System.currentTimeMillis()
                val duration = endTime - startTime

                // Toast з часом сортування
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@MainActivity,
                        "Sorting completed in ${duration}ms",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // сортування бульбашкою
    private fun bubbleSort(arr: IntArray) {
        for (i in arr.indices) {
            for (j in 0 until arr.size - i - 1) {
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j + 1]
                    arr[j + 1] = temp
                }
            }
        }
    }
}