package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.model.Book
import com.example.newsapp.model.BookResponse
import com.example.newsapp.networkUtil.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class BooksListViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private var _booksList: MutableLiveData<List<Book>>? = null

    val booksList: MutableLiveData<List<Book>>
        get() {
            if (_booksList == null) {
                _booksList = MutableLiveData()
            }

            return _booksList!!
        }

    private var _loading: MutableLiveData<Boolean>? = null

    val loading: MutableLiveData<Boolean>
        get() {
            if (_loading == null) {
                _loading = MutableLiveData()
            }

            return _loading!!
        }

    private var _error: MutableLiveData<String>? = null

    val error: MutableLiveData<String>
        get() {
            if (_error == null) {
                _error = MutableLiveData()
            }

            return _error!!
        }

    fun getScienceBooks() {
        _loading?.postValue(true)

        launch {
            val call: Call<BookResponse> = RetrofitClient.getBooksClient.getScienceBooks()
            call.enqueue(object : Callback<BookResponse> {

                override fun onResponse(
                    call: Call<BookResponse>?,
                    response: Response<BookResponse>?
                ) {
                    _booksList?.postValue(response?.body()?.items)
                }

                override fun onFailure(call: Call<BookResponse>?, t: Throwable?) {
                    _error?.postValue("Something went wrong. Try again.${t?.printStackTrace()}")
                }
            })

        }

        _loading?.postValue(false)
    }

}
