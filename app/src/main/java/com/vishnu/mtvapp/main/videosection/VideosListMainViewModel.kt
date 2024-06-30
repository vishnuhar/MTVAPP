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
                "https://www.mordeo.org/files/uploads/2022/05/Stranger-Things-Season-4-All-Characters-Poster-4K-Ultra-HD-Mobile-Wallpaper-scaled.jpg",
                "https://cdn.europosters.eu/image/hp/65835.jpg"),



             Video("2", "Queen", "Rani is devastated after her fiance leaves her just before the wedding", "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
                 "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/e2a840e6-fc24-4bc9-b1cb-51a5911c3e11/d515mmq-7c563d6e-dd0e-413f-a35d-e37124697b94.jpg/v1/fill/w_900,h_1273,q_75,strp/queen_poster_by_luthienmuse_d515mmq-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2UyYTg0MGU2LWZjMjQtNGJjOS1iMWNiLTUxYTU5MTFjM2UxMVwvZDUxNW1tcS03YzU2M2Q2ZS1kZDBlLTQxM2YtYTM1ZC1lMzcxMjQ2OTdiOTQuanBnIiwiaGVpZ2h0IjoiPD0xMjczIiwid2lkdGgiOiI8PTkwMCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS53YXRlcm1hcmsiXSwid21rIjp7InBhdGgiOiJcL3dtXC9lMmE4NDBlNi1mYzI0LTRiYzktYjFjYi01MWE1OTExYzNlMTFcL2x1dGhpZW5tdXNlLTQucG5nIiwib3BhY2l0eSI6OTUsInByb3BvcnRpb25zIjowLjQ1LCJncmF2aXR5IjoiY2VudGVyIn19.zcgKvE7GIQ0G-o8E_J9FLJwB5zSDb8gcZ0eB_be92J0",
                 "https://cdn.europosters.eu/image/hp/69083.jpg")


        )
    }
}