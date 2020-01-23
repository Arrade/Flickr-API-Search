package com.example.flickr_example.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickr_example.network.ObjApi
import com.example.flickr_example.network.jsontokotlin.InfoProperties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class InfoViewModel : ViewModel() {

    private val _propertiesInfo = MutableLiveData<InfoProperties>()

    val propertiesInfo : LiveData<InfoProperties>
        get() = _propertiesInfo

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    fun grabInfo(apiKey: String, photo_id: String) {
        coroutineScope.launch {

            try {
                val infoProperties = ObjApi.retrofitService.info(apiKey, photo_id)
                _propertiesInfo.value = infoProperties
            } catch (e: Exception) {
                _propertiesInfo.value = null
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}