package com.github.mishkaff89.practicumdev.search.adpters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.mishkaff89.practicumdev.databinding.ItemSearchBinding
import com.github.mishkaff89.practicumdev.news.adapters.NewsDiffUtils
import com.github.mishkaff89.practicumdev.news.data.NewsCharity
import com.github.mishkaff89.practicumdev.news.data.NewsItem

class EventsAdapter(
    private var events: List<NewsItem>,
    private val onItemClick: (Int) -> Unit):
    RecyclerView.Adapter<EventsAdapter.EventsHolder>() {


    class EventsHolder(private val binding:ItemSearchBinding, private val onItemClick: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun setData(item: NewsItem){
            binding.tvResult.text = item.title
            itemView.setOnClickListener { onItemClick(item.id) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context)
        ,parent,false)
        return EventsHolder(view, onItemClick)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.setData(events[position])
    }

    fun updateEvents(newData: List<NewsItem>){
        val callback = NewsDiffUtils(events, newData)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
        events = newData
    }
}