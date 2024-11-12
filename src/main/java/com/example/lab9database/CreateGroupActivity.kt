package com.example.lab9database

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab9database.room.AppDatabase
import com.example.lab9database.room.Group
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.lab9database.databinding.ActivityCreateGroupBinding

class CreateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGroupBinding
    private val database by lazy { AppDatabase.getDatabase(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreateGroup.setOnClickListener {
            val groupName = binding.etGroupName.text.toString()
            if (groupName.isNotEmpty()) {
                CoroutineScope(Dispatchers.IO).launch {
                    val group = Group(name = groupName)
                    database.groupDao().insert(group)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(applicationContext, "Group created", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}