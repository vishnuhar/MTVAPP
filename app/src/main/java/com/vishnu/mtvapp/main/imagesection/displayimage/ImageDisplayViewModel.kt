package com.vishnu.mtvapp.main.imagesection.displayimage

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishnu.mtvapp.main.imagesection.model.Image
import com.vishnu.mtvapp.utils.Constants


class ImageDisplayViewModel :ViewModel() {

    private val _detailData = MutableLiveData<Image>()
    val detailData: LiveData<Image>
        get() = _detailData

    fun loadDetailImageData(detailBundle: Bundle){

            val imageUrlKey = detailBundle.getString(Constants.IMAGE_URL_KEY)

            val detailData = Image(
                "","","",imageUrlKey)
            _detailData.value = detailData


    }
}