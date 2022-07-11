package com.express.android.lwbooksearch.ui.viewmodel

import androidx.lifecycle.*
import com.bumptech.glide.load.engine.Resource
import com.express.android.lwbooksearch.api.RetroInstance
import com.express.android.lwbooksearch.model.BookListModel
import com.express.android.lwbooksearch.repository.BookRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel(private val bookRepository: BookRepository): ViewModel() {

    private var _bookList: MutableLiveData<Resource<List<BookListModel>>> = MutableLiveData()
    val bookList: MutableLiveData<Resource<List<BookListModel>>> get() = _bookList

//    var bookList: MutableLiveData<BookListModel>
//
    init {
        getBookList()
    }

//    fun getBookListObserver(): MutableLiveData<BookListModel> {
//        return bookList
//    }

    private fun getBookList() {
        viewModelScope.launch {
            bookRepository.getBooks().collect {
                _bookList.postValue(it)
            }
        }
    }
//
//    fun getBookListObserver(): MutableLiveData<BookListModel> {
//        return bookList
//    }
//
//    fun makeApiCall(query: String) {
//
//        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
//        retroInstance.getBookListFromApi(query)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(getBookListObserverRx())
//    }

//    private fun getBookListObserverRx():Observer<BookListModel> {
//        return object :Observer<BookListModel>{
//            override fun onComplete() {
//            }
//
//            override fun onError(e: Throwable) {
//                bookList.postValue(null)
//            }
//
//            override fun onNext(t: BookListModel) {
//                bookList.postValue(t)
//            }
//
//            override fun onSubscribe(d: Disposable) {
//            }
//        }
//    }
}

class BookViewModelFactory(
    private val bookRepository: BookRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(bookRepository) as T
    }
}