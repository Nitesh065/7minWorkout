package com.example.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit  var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.flStart.setOnClickListener {
            Toast.makeText(this,"This is the start of the app",Toast.LENGTH_SHORT).show()
        val intent = Intent(this,ExerciseActivity::class.java)
        startActivity(intent)
    }
        binding.flBMI.setOnClickListener {
            Toast.makeText(this,"This is the start of the app",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,BMI::class.java)
            startActivity(intent)
        }
        binding.flHistory.setOnClickListener {
            Toast.makeText(this,"This is the start of the app",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,History::class.java)
            startActivity(intent)
        }

    }
}