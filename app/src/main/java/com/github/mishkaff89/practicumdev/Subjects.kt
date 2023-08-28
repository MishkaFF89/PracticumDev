package com.github.mishkaff89.practicumdev

import android.content.Context
import com.github.mishkaff89.practicumdev.news.data.NewsItem
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import io.reactivex.rxjava3.schedulers.Schedulers

class Subjects(appContext: Context) {
    var subjects: List<Subject>? = null

    init {
        Utils.getEventsRxJava(appContext)
            .map { events -> events.news.map { event -> Subject(event, false) } }
            .subscribeOn(Schedulers.io())
            .subscribe{subjects ->
                this.subjects = subjects
            }
    }

    fun setAsRead(id: Int){
        subjects?.find { subject -> subject.event.id == id }?.isRead = true
    }

    data class Subject(
        val event: NewsItem,
        var isRead: Boolean
    )
}