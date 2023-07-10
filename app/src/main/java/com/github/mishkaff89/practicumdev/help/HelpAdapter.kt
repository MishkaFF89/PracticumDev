package com.github.mishkaff89.practicumdev.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mishkaff89.practicumdev.databinding.ItemCategoryBinding

class HelpAdapter(category: List<HelpCategoryItem>) :
    RecyclerView.Adapter<HelpAdapter.HelpHolder>() {
    private val listCategory = category

    class HelpHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: HelpCategoryItem) = with(binding) {
            tvCategoryTitle.text = item.title
            ivLogoCategory.setImageResource(item.imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return HelpHolder(view)
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    override fun onBindViewHolder(holder: HelpHolder, position: Int) {
        holder.setData(listCategory[position])
    }
}