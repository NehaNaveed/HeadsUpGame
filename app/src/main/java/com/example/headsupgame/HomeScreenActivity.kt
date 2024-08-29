package com.example.headsupgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.headsupgame.databinding.ActivityHomeScreenBinding
import com.example.headsupgame.viewModel.HomeScreenViewModel

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ParentAdapter
    private val homeVm by lazy {
        ViewModelProvider(this)[HomeScreenViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding: ActivityHomeScreenBinding = ActivityHomeScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        recyclerView = binding.parentRecycler
        homeVm.decksList.observe(this) { decks ->
            val adapter = ParentAdapter(decks, onClick = {
                startActivity(Intent(this, GameScreenActivity::class.java))
            })
            recyclerView.adapter = adapter
        }

    }
}