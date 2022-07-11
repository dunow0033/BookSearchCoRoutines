package com.express.android.lwbooksearch.repository

import com.express.android.lwbooksearch.utils.Resource
import com.express.android.lwbooksearch.api.ApiManager
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class BookRepository(private val apiManager: ApiManager) {
    fun getBooks() = flow {

        val resource = try {
            val response = apiManager.getBooks()
            if(response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (ex: Exception) {
            Resource.Error(ex.toString())
        }
        emit(resource)
    }
}