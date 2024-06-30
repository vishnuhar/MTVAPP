package com.vishnu.mtvapp.main.videosection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishnu.mtvapp.main.videosection.model.Video

class VideosListMainViewModel : ViewModel() {
    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    init {
        _videos.value = loadVideos()
    }

    private fun loadVideos(): List<Video> {
        return listOf(
            Video("1", "Stranger Thing",
                "Finn Wolfhard said that 'Stranger Things' Season 5 may arrive in 2025 and he would be 22 by that time.",
                "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
                "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg",
                "https://cdn.europosters.eu/image/hp/65835.jpg"),



             Video("2", "Queen", "Rani is devastated after her fiance leaves her just before the wedding", "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
                 "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
                 "https://cdn.europosters.eu/image/hp/69083.jpg")


        )
    }
}