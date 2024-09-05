package com.example.headsupgame.presentation.display.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.headsupgame.HeadsUpData
import com.example.headsupgame.dataModels.DecksData

class HomeScreenViewModel(): ViewModel() {
    private val _decksList = MutableLiveData<List<DecksData>>()
    val decksList: LiveData<List<DecksData>> = _decksList

    init {
        _decksList.value = HeadsUpData.decksData
    }
}