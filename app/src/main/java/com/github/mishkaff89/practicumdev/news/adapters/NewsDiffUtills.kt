package com.github.mishkaff89.practicumdev.news.adapters

import androidx.recyclerview.widget.DiffUtil
import com.github.mishkaff89.practicumdev.news.data.NewsItem

class NewsDiffUtils(private val oldList: List<NewsItem>,
                     private val newList: List<NewsItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].categoryId == newList[newItemPosition].categoryId
    }
}