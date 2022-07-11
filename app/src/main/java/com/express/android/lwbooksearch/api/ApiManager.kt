package com.express.android.lwbooksearch.api

class ApiManager {
    private val bookService: BookService
    private val retrofit = RetroInstance.providesRetrofit()

    init {
        bookService = retrofit.create(BookService::class.java)
    }

    suspend fun getBooks() = bookService.getBookListFromApi()
}