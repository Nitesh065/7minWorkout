package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMI : AppCompatActivity() {
    private var binding: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmi)
        supportActionBar?.title = "CALCULATE BMI"

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarBmi?.setNavigationOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.btnBmiCalculate?.setOnClickListener {
            if (validMetricUnit()){

                val heightValue: Float = binding?.etBmiHeight?.text.toString().toFloat() /100

                val weightValue: Float = binding?.etBmiWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)

                displayBmi(bmi)

            }
            else{
                Toast.makeText(this,"Please enter valid values",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun displayBmi(bmi: Float){
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }
        else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        }
        else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        }
        else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0){
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        }
        else if(bmi.compareTo(35f) >0 && bmi.compareTo(40f) <= 0){
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }
        else{
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBmiResult?.visibility = View.VISIBLE

        binding?.tvBmiValue?.text = bmiValue
        binding?.tvBmiType?.text = bmiLabel
        binding?.tvBmiDescription?.text = bmiDescription
    }

    private fun validMetricUnit(): Boolean{
        var isValid = true
        if (binding?.etBmiWeight?.text.toString().isEmpty()){
            isValid = false
        }
        else if (binding?.etBmiHeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
}