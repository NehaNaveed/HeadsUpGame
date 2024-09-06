package com.example.headsupgame.presentation.display.game

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.headsupgame.R
import com.example.headsupgame.databinding.FragmentGameBinding


class GameFragment : Fragment() , SensorEventListener {

    private lateinit var binding: FragmentGameBinding
    private val gameVm by lazy {
        ViewModelProvider(this)[GameViewModel::class.java]
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bundle = Bundle()
        gameVm.correctCount.observe(viewLifecycleOwner) { count ->
             bundle = Bundle().apply {
                 Log.d("CORRECT VALUE","$count")
                putString("count", count.toString())
            }
        }
        gameVm.timerText.observe(viewLifecycleOwner) { time ->
            binding.timer.text = time
            if (binding.timer.text == "DONE!"){
                findNavController().navigate(R.id.action_gameFragment_to_resultsFragment,bundle)
            }
        }
        gameVm.sensorOutput.observe(viewLifecycleOwner) { output ->
            binding.wordToGuess.text = output
            when (output) {
                "Pass" -> binding.main.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.failColor) // Change to your defined color
                )

                "Correct!" -> binding.main.setBackgroundColor(
                    ContextCompat.getColor(requireContext(), R.color.green) // Change to your defined color
                )

                else -> binding.main.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.gradient_back

                )
            }

        }


    }
    override fun onResume() {
        super.onResume()
        gameVm.startSensor(this)

    }

    override fun onPause() {
        super.onPause()
        gameVm.stopSensor(this)

        activity?.requestedOrientation = SCREEN_ORIENTATION_UNSPECIFIED
    }
    override fun onSensorChanged(event: SensorEvent) {
        val category = arguments?.getString("name")
        if (category != null) {
            gameVm.processSensorData(event,category)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.requestedOrientation = SCREEN_ORIENTATION_UNSPECIFIED

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}

