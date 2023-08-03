package com.github.mishkaff89.practicumdev.news

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.github.mishkaff89.practicumdev.news.data.FilterNewsCategories
import com.github.mishkaff89.practicumdev.news.helpers.Constants
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import java.lang.Exception

class CategoriesIntentService: IntentService("") {
    override fun onHandleIntent(intent: Intent?) {
        var categories:FilterNewsCategories? = null
        try {
            categories = Utils.getCategories(this.applicationContext)
        } catch (e:Exception){
            Log.e("",e.message.orEmpty())
        }
        val responseIntent = Intent().apply {
            action = Constants.ACTION_SEND_CATEGORIES
            addCategory(Intent.CATEGORY_DEFAULT)
            putExtra(Constants.EXTRA_CATEGORIES, categories)
        }
        sendBroadcast(responseIntent)
    }
}