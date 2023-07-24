package com.github.mishkaff89.practicumdev.news.helpers

import com.github.mishkaff89.practicumdev.news.data.FilterCategory

fun interface Filtering {
    fun onFiltersChanged(onFiltersChanged:(List<FilterCategory>) -> Unit)
}