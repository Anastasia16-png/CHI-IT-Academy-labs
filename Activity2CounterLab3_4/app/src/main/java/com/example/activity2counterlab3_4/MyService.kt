package com.example.activity2counterlab3_4

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val counter = intent?.getIntExtra("counter", 0) ?: 0
        // Тут ви можете обробити лічильник (зберегти в базі даних, надіслати на сервер і т.д.)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}