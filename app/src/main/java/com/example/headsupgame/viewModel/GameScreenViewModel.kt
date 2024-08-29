package com.example.headsupgame

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameScreenViewModel(application: Application) : AndroidViewModel(application) {

    private val _timerText = MutableLiveData<String>()
    val timerText: LiveData<String> = _timerText

    private val _sensorOutput = MutableLiveData<String>()
    val sensorOutput: LiveData<String> = _sensorOutput

    private val sensorManager: SensorManager =
        application.getSystemService(SensorManager::class.java)
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val countDownTimer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _timerText.value = "Timer : ${millisUntilFinished / 1000}"
        }

        override fun onFinish() {
            _timerText.value = "DONE!"
        }
    }

    init {
        countDownTimer.start()
    }

    fun startSensor(listener: SensorEventListener) {
        accelerometer?.let {
            sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopSensor(listener: SensorEventListener) {
        sensorManager.unregisterListener(listener)
    }

    fun processSensorData(event: SensorEvent) {
        val z = event.values[2]
        _sensorOutput.value = when {
            z > 7 -> "Pass"
            z < -7 -> "Fail"
            else -> "Word to Guess"
        }
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }
}
