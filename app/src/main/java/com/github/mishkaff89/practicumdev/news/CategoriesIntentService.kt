package com.github.mishkaff89.practicumdev.news

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.github.mishkaff89.practicumdev.news.data.FilterNewsCategories
import com.github.mishkaff89.practicumdev.news.helpers.Constants
import com.github.mishkaff89.practicumdev.news.helpers.Utils
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception

class CategoriesIntentService: IntentService("") {
    override fun onHandleIntent(intent: Intent?) {
        var categories:FilterNewsCategories? = null
        try {
            Utils.getCategoriesRxJava(this.applicationContext)
                .map { it.categories!! }
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe {
                    val responseIntent = Intent().apply {
                        action = Constants.ACTION_SEND_CATEGORIES
                        addCategory(Intent.CATEGORY_DEFAULT)
                        putExtra(Constants.EXTRA_CATEGORIES, categories)
                    }
                    sendBroadcast(responseIntent)
                }

        } catch (e:Exception){
            Log.e("",e.message.orEmpty())
        }
    }
}