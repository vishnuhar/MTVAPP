package com.vishnu.mtvapp.main

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
            Video("1", "Stranger Things",
                "Finn Wolfhard said that 'Stranger Things' Season 5 may arrive in 2025 and he would be 22 by that time.",
                "https://www.classicgaming.cc/classics/dragons-lair/files/videos/stranger-things-season-2-trailer.mp4",
                "https://www.mordeo.org/files/uploads/2022/05/Stranger-Things-Season-4-All-Characters-Poster-4K-Ultra-HD-Mobile-Wallpaper-scaled.jpg",
                "https://cdn.europosters.eu/image/hp/65835.jpg"),



             Video("2", "Dragon Liars", "Rani is devastated after her fiance leaves her just before the wedding", "https://www.classicgaming.cc/classics/dragons-lair/files/videos/Dragons-Lair-Intro.mp4",
                 "https://dyn1.heritagestatic.com/lf?set=path%5B2%2F2%2F3%2F6%2F3%2F22363856%5D%2Csizedata%5B850x600%5D&call=url%5Bfile%3Aproduct.chain%5D",
                 "https://static1.cbrimages.com/wordpress/wp-content/uploads/2023/01/dragon-s-lair-cover.jpg?q=50&fit=contain&w=1140&h=&dpr=1.5")


            ,

            Video("3", "Action Movie",
                "A powerful, dark and intense title design, suitable for Hollywood type of movie projects, useful for drama, fantasy, thriller, mystery, or superhero Sci-Fi movie and tv series promo, commercial or tv spot.",
                "https://previews.customer.envatousercontent.com/h264-video-previews/1c67bf17-b0b0-444d-be9a-7369313bda2a/599548.mp4?_=2",
                "https://cdn.daily-sun.com/public/news_images/2020/05/05/Daily-sun-25.jpg",
                "https://cdn.daily-sun.com/public/news_images/2020/05/05/Daily-sun-25.jpg"),

        )



    }
}