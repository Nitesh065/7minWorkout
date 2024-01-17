package com.example.a7minutesworkout

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a7minutesworkout.databinding.ActivityExerciseBinding
import java.lang.Exception
import java.util.Locale

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private  var binding: ActivityExerciseBinding? = null

   private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech?  = null

    private var player: MediaPlayer? = null

    private var exerciseStatusAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        // tool bar to get back to screen when back button is pressed
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExercise()
        binding?.toolbarExercise?.setNavigationOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        tts = TextToSpeech(this, this)


      setupRestView()

        setupExerciseStatusRecycleView()


    }

    private fun setupExerciseStatusRecycleView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        exerciseStatusAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseStatusAdapter
    }

    private fun setupRestView(){
        try {
          //  val soundUri = Uri.parse("android.resources://a7minutesworkout/" + R.raw.press_start)
            player = MediaPlayer.create(applicationContext,R.raw.press_start)
            player?.isLooping = false
            player?.start()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingExercise?.visibility = View.VISIBLE
        binding?.tvUpcomingTitle?.visibility = View.VISIBLE
        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }

        //speakOut("Rest ten seconds. Get ready for ${exerciseList!![currentExercisePosition + 1].getName()}")
        binding?.tvUpcomingExercise?.text = exerciseList!![currentExercisePosition + 1].getName()
        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvUpcomingExercise?.visibility = View.INVISIBLE
        binding?.tvUpcomingTitle?.visibility = View.INVISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE

        if (exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text = exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millsUnitFinish: Long) {
                  restProgress++
                    binding?.progressBar?.progress = 10 - restProgress
                    binding?.tvTimer?.text =  (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
              setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar() {
        binding?.exerciseProgressBar?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millsUnitFinish: Long) {
                exerciseProgress++
                binding?.exerciseProgressBar?.progress = 30 - exerciseProgress
                binding?.tvExerciseTimer?.text =  (30 - exerciseProgress).toString()
            }

            override fun onFinish() {

                if (currentExercisePosition < exerciseList!!.size - 1){
                    setupRestView()
                }
                else{
                    Toast.makeText(this@ExerciseActivity,"Congratulations!! you have completed the 7 minutes workout",Toast.LENGTH_SHORT).show()
                }


            }

        }.start()
    }

    override fun onDestroy() {

        if (restTimer != null){
           restTimer?.cancel()
           restProgress = 0
       }
        if (tts != null){
            tts?.stop()
            tts?.shutdown()
            binding = null
        }
        if (player != null){
            player!!.stop()
        }

        binding = null
        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
          val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTs","The language enter is not sported")
            }
            else{
                Log.e("TTs","Initialization Failed")
            }
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
}