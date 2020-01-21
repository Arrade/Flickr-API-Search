package com.example.flickr_example.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickr_example.network.ObjApi
import com.example.flickr_example.network.jsontokotlin.SearchProperties
import kotlinx.coroutines.*
import java.lang.Exception

enum class CurrentApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<CurrentApiStatus>()

    private val _properties = MutableLiveData<SearchProperties>()

    val properties: LiveData<SearchProperties>
        get() = _properties

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    fun performSearch(apikey: String, query: String) {
        coroutineScope.launch {

            try {
                _status.value = CurrentApiStatus.LOADING
                val searchProperties= ObjApi.retrofitService.search(apikey, query)
                _status.value = CurrentApiStatus.DONE
                _properties.value = searchProperties
            } catch (e: Exception) {
                _status.value = CurrentApiStatus.ERROR
                _properties.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
