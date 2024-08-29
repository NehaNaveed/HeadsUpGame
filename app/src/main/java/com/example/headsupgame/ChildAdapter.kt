package com.example.headsupgame

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.headsupgame.dataModels.CategoryData
import com.example.headsupgame.databinding.ItemsNestedRecBinding


class ChildAdapter(private var catList: List<CategoryData>, private var onItemClick :(CategoryData) -> Unit ) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    inner class ChildViewHolder (private val binding: ItemsNestedRecBinding, private var onItemClick :(CategoryData) -> Unit  ): RecyclerView.ViewHolder(binding.root){
        fun bind(cat : CategoryData){
            binding.titleCategory.text = cat.title
            Glide.with(itemView.context)
                .load(cat.image)
                .into(binding.imageCategory)
            Log.d("IMAGE RESULT", "image : ${binding.imageCategory}")
            binding.imageCategory.setOnClickListener(){
                onItemClick.invoke(cat)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildViewHolder {
        val binding = ItemsNestedRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ChildAdapter.ChildViewHolder, position: Int) {
        holder.bind(catList[position])
    }
    override fun getItemCount(): Int {
        return catList.count()
    }
}