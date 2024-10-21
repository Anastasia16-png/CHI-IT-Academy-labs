package com.example.broadcastreciverlab3_4

import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MyBroadcastReceiver", "Broadcast received!")
        if (context != null) {
            val localIntent = Intent("com.example.LOCAL_BROADCAST")
            localIntent.putExtra("message", "Broadcast received!")
            LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent)
            Log.d("MyBroadcastReceiver", "Local broadcast sent!")
        } else {
            Log.e("MyBroadcastReceiver", "Context is null!")
        }
    }
}