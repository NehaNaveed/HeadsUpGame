package com.example.headsupgame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.headsupgame.dataModels.CategoryData
import com.example.headsupgame.dataModels.DecksData
import com.example.headsupgame.databinding.ItemsRecyclerBinding

//list adapter
class ParentAdapter(private var decksList: List<DecksData>,private var onClick :(CategoryData) -> Unit ) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {
    inner class ParentViewHolder(val binding: ItemsRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(decksData: DecksData) {
            binding.categoryTitle.text = decksData.name
            val childAdapter = ChildAdapter(decksData.categoryList, decksData.name,onClick)
            binding.childRecycler.adapter = childAdapter


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ItemsRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentAdapter.ParentViewHolder, position: Int) {
        holder.bind(decksList[position])

    }

    override fun getItemCount(): Int {
        return decksList.count()
    }
}