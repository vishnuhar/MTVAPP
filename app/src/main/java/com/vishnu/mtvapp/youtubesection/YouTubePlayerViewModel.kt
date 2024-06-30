package com.vishnu.mtvapp.youtubesection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishnu.mtvapp.youtubesection.model.YouTube

class YouTubePlayerViewModel : ViewModel(){

    private val _youTube = MutableLiveData<List<YouTube>>()
    val youTube: LiveData<List<YouTube>> get() = _youTube

    init {
        _youTube.value = loadVideos()
    }

    private fun loadVideos(): List<YouTube> {
        return listOf(
            YouTube("https://logowik.com/content/uploads/images/373_you_tube_new.jpg","https://www.youtube.com/watch?v=gslccIy0E6M","The Daily Aviation","\"US Pilots Rush for Their Massive Stealth Bombers and Takeoff at Full Throttl",
                "https://static1.simpleflyingimages.com/wordpress/wp-content/uploads/2024/04/ecodemonstrator_explorer787_boeing-1.jpg?q=49&fit=contain&w=1140&h=&dpr=2")
        )

    }
}