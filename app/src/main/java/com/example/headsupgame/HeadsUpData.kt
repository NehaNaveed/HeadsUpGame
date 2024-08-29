package com.example.headsupgame

import com.example.headsupgame.dataModels.CategoryData
import com.example.headsupgame.dataModels.DecksData

object HeadsUpData {

    private val categoryData = listOf(
        CategoryData("Sports",Images.sportsImg),
        CategoryData("Professions",Images.profImg),
        CategoryData("Animals",Images.animImg),
        CategoryData("Celebrities",Images.celebImg),
        CategoryData("Tv Shows",Images.tvImg),
    )

    private val videoCategory = listOf(
        CategoryData("Step 1",Images.step1),
        CategoryData("Step 2",Images.step2),
        CategoryData("Step 3",Images.step3),
        CategoryData("Step 4",Images.step4),
        CategoryData("Step 5",Images.step5),
    )

    val decksData = listOf(
        DecksData("My Decks", categoryData),
        DecksData("How to Play:", videoCategory)
    )
}