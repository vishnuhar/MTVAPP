package com.vishnu.mtvapp.youtubesection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishnu.mtvapp.main.videosection.model.Video
import com.vishnu.mtvapp.youtubesection.model.YouTube

class YouTubePlayerViewModel : ViewModel(){

    private val _youTube = MutableLiveData<List<YouTube>>()
    val youTube: LiveData<List<YouTube>> get() = _youTube

    init {
        _youTube.value = loadVideos()
    }

    private fun loadVideos(): List<YouTube> {
        return listOf(
            YouTube("https://cdn.pixabay.com/photo/2021/09/11/18/21/youtube-6616310_1280.png","https://www.youtube.com/watch?v=gslccIy0E6M","YouTube",
                "https://mrwallpaper.com/images/high/black-youtube-banner-bhadojaxac5w2ygv.webp")
        )

    }
}