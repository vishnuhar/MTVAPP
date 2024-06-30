package com.vishnu.mtvapp.main.videosection.player

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishnu.mtvapp.utils.Constants
import com.vishnu.mtvapp.main.videosection.model.Video
import com.vishnu.mtvapp.old.VideoDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoPlayerBundleViewModel : ViewModel() {


    private val _detailData = MutableLiveData<Video>()
    val detailData: LiveData<Video>
        get() = _detailData

    fun loadDetailData(detailBundle: Bundle){

            val idKey = detailBundle.getString(Constants.ID_KEY)
            val titleKey = detailBundle.getString(Constants.TITLE_KEY)
            val descKey = detailBundle.getString(Constants.DESC_KEY)
            val videoUrlKey = detailBundle.getString(Constants.VIDEO_URL_KEY)
            val thumbnailKey = detailBundle.getString(Constants.THUMBNAIL_KEY)
            val detailData = Video(
                idKey.toString(),titleKey.toString(),descKey.toString(),videoUrlKey.toString(),thumbnailKey.toString())
            _detailData.value = detailData


    }
}