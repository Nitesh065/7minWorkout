package com.example.a7minutesworkout

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private  var binding: ActivityExerciseBinding? = null

   private var restTimer: CountDownTimer? = null
    private var timerDuration: Long = 10000
    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        // tool bar to get back to screen when back button is pressed
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.tvTimer?.text = (timerDuration/1000).toString()

      setupRestView()

    }

    private fun setupRestView(){
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(timerDuration, 1000){
            override fun onTick(millsUnitFinish: Long) {
                  restProgress++
                    binding?.progressBar?.progress = 10 - restProgress
                    binding?.tvTimer?.text =  (10 - restProgress).toString()
            }

            override fun onFinish() {
               Toast.makeText(this@ExerciseActivity,"Timer is Finished",Toast.LENGTH_SHORT).show()
            }

        }.start()
    }

    override fun onDestroy() {

        if (restTimer != null){
           restTimer?.cancel()
           restProgress = 0
       }
        binding = null
        super.onDestroy()
    }
}