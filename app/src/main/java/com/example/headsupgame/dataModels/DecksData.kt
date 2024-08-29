package com.example.headsupgame.dataModels

import androidx.room.Entity
import androidx.room.PrimaryKey

data class DecksData(
    var name:String,
    var categoryList: List<CategoryData>
    )
