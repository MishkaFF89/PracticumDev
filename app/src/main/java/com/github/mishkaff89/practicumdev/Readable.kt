package com.github.mishkaff89.practicumdev

interface Readable {

    val subjects: List<Subjects.Subject>

    fun setNotificationBadge(count: Int)

    fun setAsRead(id: Int)
}