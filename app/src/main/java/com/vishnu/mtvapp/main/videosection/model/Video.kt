package com.vishnu.mtvapp.main.videosection.model

data class Video(
    val id: String,
    val title: String,
    val description: String,
    val videoUrl:String,
    val thumbnailUrl: String?,
    val backgroundUrl: String?
)