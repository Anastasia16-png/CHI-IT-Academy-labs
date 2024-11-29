package com.example.broadcastreciverlab3_4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    private lateinit var myReceiver: MyBroadcastReceiver
    private lateinit var broadcastMessage: TextView

    private val localBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val message = intent?.getStringExtra("message")
            Log.d("LocalBroadcastReceiver", "Received local broadcast: $message")
            broadcastMessage.text = message ?: "Повідомлення не отримано"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate called")

        myReceiver = MyBroadcastReceiver()
        broadcastMessage = findViewById(R.id.broadcastMessage)

        val button: Button = findViewById(R.id.sendBroadcastButton)
        button.setOnClickListener {
            Log.d("MainActivity", "Send Broadcast button clicked")
            val intent = Intent("com.example.MY_ACTION")
            sendBroadcast(intent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume called")
        // Реєстрація BroadcastReceiver з фільтром
        val filter = IntentFilter("com.example.MY_ACTION")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(myReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(myReceiver, filter, RECEIVER_EXPORTED)
        }
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(localBroadcastReceiver, IntentFilter("com.example.LOCAL_BROADCAST"))
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause called")
        unregisterReceiver(myReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(localBroadcastReceiver)
    }
}

