package com.github.mishkaff89.practicumdev

import java.lang.Exception
import java.util.Calendar
import java.util.Date

//2
enum class Type {
    DEMO, FULL
}

//3
data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val type: Type,
) {
    val startTime: Date by lazy {
        Calendar.getInstance().time
    }
}

//9
interface AuthCallBack {
    fun authSuccess()
    fun authFailed()
}


fun main() {
    //4
    val user = User(0, "Mikhail", 33, Type.FULL)
    println(user.startTime.toString())
    Thread.sleep(1000)
    println(user.startTime.toString())
    //5
    val userList = mutableListOf(user)
    userList.apply {
        this.add(User(1, "Anton", 32, Type.DEMO))
        this.add(User(2, "Maxim", 30, Type.FULL))
        this.add(User(3, "Sergey", 34, Type.DEMO))
    }
    //6
    val usersWithFullType = userList.filter { user -> user.type == Type.FULL }
    //7
    val users = userList.map { user -> user.name }
    println(users.first())
    println(users.last())

   

}
fun User.checkAge() {
    if (this.age >= 18) {
        println("User is adult")
    } else {
        throw Exception("User is young")
    }
}

val authCallBack = object : AuthCallBack {
    override fun authSuccess() {
        println("Auth success")
    }
    override fun authFailed() {
        println("Auth failed")
    }
}

//10
fun updateCache() {
    println("the cache is being updated")
}

inline fun auth(updateCache: () -> Unit){
    try {
        authCallBack.authSuccess()
        updateCache()
    } catch (e: Exception){
        authCallBack.authFailed()
    }

}

//13
fun doAction(action: Action) {
    when (action) {
        is Registration -> println("Auth started")
        is Login -> auth { updateCache() }
        is LogOut -> println("LogOut")
    }
}


//12
sealed class Action()
class Registration() : Action()
class Login(user: User) : Action()
class LogOut() : Action()




