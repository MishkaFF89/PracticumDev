package com.github.mishkaff89.practicumdev.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mishkaff89.practicumdev.databinding.ItemSearchBinding

class SearchAdapter(result: List<SearchItem>) :
    RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    private val listSearch = result

    class SearchHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: SearchItem)= with(binding){
            tvResult.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context),
        parent, false)
        return SearchHolder(view)
    }

    override fun getItemCount(): Int {
        return listSearch.size
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        holder.setData(listSearch[position])
    }
}