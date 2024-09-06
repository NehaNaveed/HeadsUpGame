package com.example.headsupgame.presentation.display.game

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.headsupgame.dataModels.Words

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val _timerText = MutableLiveData<String>()
    val timerText: LiveData<String> = _timerText
    private val randomWords = Words()

    private val _sensorOutput = MutableLiveData<String>()
    val sensorOutput: LiveData<String> = _sensorOutput

    private val _correctCount = MutableLiveData<Int>().apply { value = 0  }
    val correctCount: LiveData<Int> = _correctCount

    private val sensorManager: SensorManager =
        application.getSystemService(SensorManager::class.java)
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val countDownTimer = object : CountDownTimer(40000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _timerText.value = "Timer : ${millisUntilFinished / 1000}"
        }

        override fun onFinish() {
            _timerText.value = "DONE!"
        }
    }
    private var lastSensorOutput = ""

    init {
        countDownTimer.start()
    }

    fun startSensor(listener: SensorEventListener) {
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

    }

    fun stopSensor(listener: SensorEventListener) {
        sensorManager.unregisterListener(listener)

    }

    fun processSensorData(event: SensorEvent, category: String) {
        val z = event.values[2]
        var wordName: List<String> = listOf()
        when (category) {
            "Sports" -> wordName = randomWords.sports
            "Animals" -> wordName = randomWords.animals
            "Professions" -> wordName = randomWords.professions
            "Act It Out" -> wordName = randomWords.actions
            "Fruits" -> wordName = randomWords.fruits

        }
        _sensorOutput.value = when {
            z > 8 && z <= 12  -> {
                lastSensorOutput = "Pass"
                "Pass"
            }

            z < -6 && z >= -12  -> {
                updateCorrectCount()
                lastSensorOutput = "Correct!"
                "Correct!"
            }

            else -> when (lastSensorOutput) {
                "Pass", "Correct!" -> {
                    lastSensorOutput = "Other"
                    wordName.random()
                }
                else -> _sensorOutput.value

            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }


    private fun updateCorrectCount() {
        if (this.lastSensorOutput.contains("Other")) {
            _correctCount.value = _correctCount.value?.plus(1)
        }
    }
}
