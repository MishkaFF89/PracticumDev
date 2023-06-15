package com.github.mishkaff89.practicumdev

// Task 2
interface Publication {
    val price: Double
    val wordCount: Int
    fun getType(): String
}

data class Book(override val price: Double, override val wordCount: Int) : Publication {
    override fun getType(): String {
        val bookType = when (wordCount) {
            in 1..1000 -> "Flash Fiction"
            in 1001..7500 -> "Shorty Story"
            else -> "Novel"
        }
        return bookType
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Book)
            return false
        return price == other.price && wordCount == other.wordCount
    }
}

data class Magazine(override val price: Double, override val wordCount: Int) : Publication {
    override fun getType() = "Magazine"
}

// Task 3
fun main() {
    val book1 = Book(750.0, 1200)
    val book2 = Book(750.0, 1200)
    val magazine = Magazine(100.0, 350)
    println("Book1: type - ${book1.getType()}; word count - ${book1.wordCount}; price - ${book1.price}€")
    println("Book2: type - ${book2.getType()}; word count - ${book2.wordCount}; price - ${book2.price}€")
    println("Magazine: type - ${magazine.getType()}; word count - ${magazine.wordCount} price - ${magazine.price}€")

    // Task 4
    fun buy(publication: Publication) {
        println("The purchase is complete. The purchase amount was ${publication.price}€")
    }

    val firsBook: Book? = null
    val secondBook: Book? = Book(223.0, 1000)

    firsBook?.let { buy(it) } ?: "Null"
    secondBook?.let { buy(it) } ?: "Null"

    // Task 5
    val sum = { a: Int, b: Int -> println(a + b) }
    sum.invoke(5, 4)

    println(book1.equals(book2))
}