package com.github.mishkaff89.practicumdev.news.helpers

import android.content.Context
import android.util.Log
import com.github.mishkaff89.practicumdev.news.data.FilterNewsCategories
import com.github.mishkaff89.practicumdev.news.data.NewsCharity
import com.google.gson.Gson
import java.time.Instant
import java.time.Month
import java.time.ZoneId
import java.time.ZonedDateTime

object Utils {

    private const val CATEGORIES_JSON = "categories.json"
    private const val CHARITY_EVENTS_JSON = "events.json"

    fun getCategories(appContext: Context): FilterNewsCategories {
        try {
            val data = appContext.assets
                .open(CATEGORIES_JSON)
                .bufferedReader()

            val gson = Gson()
            return gson.fromJson(data, FilterNewsCategories::class.java)
        } catch (e: Exception) {
            Log.e("", e.message.orEmpty())
            throw e
        }
    }

    fun getNews(appContext: Context): NewsCharity {
        try {
            val data = appContext.assets
                .open(CHARITY_EVENTS_JSON)
                .bufferedReader()

            val gson = Gson()
            return gson.fromJson(data, NewsCharity::class.java)
        } catch (e: Exception) {
            Log.e("", e.message.orEmpty())
            throw e
        }
    }

    fun dateFormat(date: String): String {
        val now = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())
        val newsDate = ZonedDateTime.parse(date)
        val result: String
        if (newsDate.dayOfYear - now.dayOfYear < 30) {
            val month =
                if (newsDate.monthValue < 10) "0${newsDate.monthValue}" else newsDate.monthValue
            result = "Осталось " + (newsDate.dayOfYear - now.dayOfYear) + " дней (" + newsDate.dayOfMonth + "." + month + ")"
        } else {
            val month = when (newsDate.month) {
                Month.JANUARY -> "Январь"
                Month.FEBRUARY -> "Февраль"
                Month.MARCH -> "Март"
                Month.APRIL -> "Апрель"
                Month.MAY -> "Май"
                Month.JUNE -> "Июнь"
                Month.JULY -> "Июль"
                Month.AUGUST -> "Август"
                Month.SEPTEMBER -> "Сентябрь"
                Month.OCTOBER -> "Октябрь"
                Month.NOVEMBER -> "Ноябрь"
                Month.DECEMBER -> "Декабрь"
                else -> ""
            }
            result = "$month ${newsDate.dayOfMonth}, ${newsDate.year}"
        }
        return result
    }
}