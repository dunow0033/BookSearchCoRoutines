package com.express.android.lwbooksearch.api

import com.express.android.lwbooksearch.model.BookListModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface BookService {

    @GET("volumes")
    suspend fun getBookListFromApi(@Query("q") query: String): Response<BookListModel>
}