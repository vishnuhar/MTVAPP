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
            Image("1", "Employees", "DAn employee is a person who is hired by a company, organization, or community to perform a specific job in exchange for payment", "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg"),
            Image("2", "Technology", "Technology is the application of scientific knowledge to the practical aims of human life or, as it is sometimes phrased, to the change and manipulation of the human environment", "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg")
        )
    }
}

