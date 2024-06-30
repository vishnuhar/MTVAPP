package com.vishnu.mtvapp.main.imagesection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishnu.mtvapp.main.imagesection.model.Image

class ImagesViewModel : ViewModel() {
    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> get() = _images

    init {

        _images.value = loadImages()
    }

    private fun loadImages(): List<Image> {

        return listOf(
            Image("1", "Image 1", "Description 1", "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg"),
            Image("2", "Image 2", "Description 2", "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg")
            // Add more image items here
        )
    }
}

