package com.example.headsupgame

import com.example.headsupgame.dataModels.CategoryData
import com.example.headsupgame.dataModels.DecksData

object HeadsUpData {

    private val categoryData = listOf(
        CategoryData("Sports",R.drawable.sports),
        CategoryData("Professions",R.drawable.professions),
        CategoryData("Animals",R.drawable.animals),
        CategoryData("Act It Out",R.drawable.actions),
        CategoryData("Fruits",R.drawable.fruits),
    )

    private val instructions = listOf(
        CategoryData("Step 1",R.drawable.step1),
        CategoryData("Step 2",R.drawable.step2),
        CategoryData("Step 3",R.drawable.step3),
        CategoryData("Step 4",R.drawable.step4),
        CategoryData("Step 5",R.drawable.step5),
    )

    val decksData = listOf(
        DecksData("My Decks", categoryData),
        DecksData("How to Play:", instructions)
    )
}
