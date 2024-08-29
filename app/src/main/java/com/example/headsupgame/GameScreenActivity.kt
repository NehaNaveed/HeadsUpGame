package com.example.headsupgame

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.headsupgame.databinding.ActivityGameScreenBinding


class GameScreenActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var binding: ActivityGameScreenBinding
    private val gameVm by lazy {
        ViewModelProvider(this)[GameScreenViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gameVm.timerText.observe(this) { time ->
            binding.timer.text = time
        }

        gameVm.sensorOutput.observe(this) { output ->
            binding.wordToGuess.text = output
        }

    }

    override fun onResume() {
        super.onResume()
        gameVm.startSensor(this)
    }

    override fun onPause() {
        super.onPause()
        gameVm.stopSensor(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            gameVm.processSensorData(it)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}