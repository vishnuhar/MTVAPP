package com.vishnu.mtvapp.main.youtubesection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YouTubePlayerActivityViewModel :ViewModel(){

    private val _videoUrl = MutableLiveData<String>()
    val videoUrl: LiveData<String> = _videoUrl

    fun setVideoUrl(url: String) {
        _videoUrl.value = url
    }
}