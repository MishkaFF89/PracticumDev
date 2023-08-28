package com.github.mishkaff89.practicumdev.search

import com.jakewharton.rxbinding4.InitialValueObservable

interface Searchable {
    var searchViewListener: InitialValueObservable<CharSequence>?
}