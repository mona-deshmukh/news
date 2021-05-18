package com.example.newsapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.newsapp.Articles
import com.example.newsapp.News
import com.example.newsapp.networkUtil.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class NewsFragmentViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private var _topHeadlines: MutableLiveData<List<Articles>>? = null

    val topHeadlines: MutableLiveData<List<Articles>>
        get() {
            if (_topHeadlines == null) {
                _topHeadlines = MutableLiveData()
            }

            return _topHeadlines!!
        }
 private var _entertainmentNews: MutableLiveData<List<Articles>>? = null

    val entertainmentNews: MutableLiveData<List<Articles>>
        get() {
            if (_entertainmentNews == null) {
                _entertainmentNews = MutableLiveData()
            }

            return _entertainmentNews!!
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

    fun getTopHeadlines() {
        _loading?.postValue(true)

        launch {
            val call: Call<News> = RetrofitClient.getClient.getTopHeadlines()
            call.enqueue(object : Callback<News> {

                override fun onResponse(
                    call: Call<News>?,
                    response: Response<News>?
                ) {
                    _topHeadlines?.postValue(response?.body()?.articles)
                }

                override fun onFailure(call: Call<News>?, t: Throwable?) {
                    _error?.postValue("Something went wrong. Try again.")
                }
            })

        }

        _loading?.postValue(false)
    }
    fun getScienceNews() {
        _loading?.postValue(true)

        launch {
            val call: Call<News> = RetrofitClient.getClient.getEntertainmentNews()
            call.enqueue(object : Callback<News> {

                override fun onResponse(
                    call: Call<News>?,
                    response: Response<News>?
                ) {
                    _entertainmentNews?.postValue(response?.body()?.articles)
                }

                override fun onFailure(call: Call<News>?, t: Throwable?) {
                    _error?.postValue("Something went wrong. Try again.")
                }
            })

        }

        _loading?.postValue(false)
    }

}
