package com.github.mishkaff89.practicumdev.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.mishkaff89.practicumdev.databinding.ItemNewsCardBinding
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import com.github.mishkaff89.practicumdev.news.data.NewsItem

class NewsAdapter(list: List<NewsItem>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private var result = list

    class NewsHolder(
        private val binding: ItemNewsCardBinding,
        private val onItemClick: (Int) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(item: NewsItem) = with(binding) {
            tvNewsTitle.text = item.title
            tvNewsDescription.text = item.description
            btnNewsDate.text = Utils.dateFormat(item.date)
            itemView.setOnClickListener {onItemClick(item.id)}
        }

    }

    fun updateNews(newList: List<NewsItem>) {
        val callback = NewsDiffUtils(result, newList)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        result = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = ItemNewsCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NewsHolder(view, onItemClick)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.setData(result[position])
    }
}