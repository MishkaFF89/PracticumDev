package com.github.mishkaff89.practicumdev.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.github.mishkaff89.practicumdev.databinding.ItemFilterCategoryBinding
import com.github.mishkaff89.practicumdev.news.data.FilterCategory

class FilterNewsAdapter(private val list: List<FilterCategory>,private val onCategorySwitch: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<FilterNewsAdapter.FilterHolder>() {

    class FilterHolder(
        private val binding: ItemFilterCategoryBinding,
        private val onCategorySwitch: (Int, Boolean) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: FilterCategory) {
            binding.tvFilterTitle.text = item.title
            binding.switchCategory.setOnCheckedChangeListener {_:CompoundButton, isChecked ->
                onCategorySwitch(item.id, isChecked)
            }
            itemView.setOnClickListener {
                binding.switchCategory.isChecked = !binding.switchCategory.isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val view = ItemFilterCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return FilterHolder(view, onCategorySwitch)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.setData(list[position])
    }
}