package com.vishnu.mtvapp.main

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vishnu.mtvapp.ImageBundleActivity
import com.vishnu.mtvapp.main.videosection.cardpresenter.CardPresenter
import com.vishnu.mtvapp.utils.Constants
import com.vishnu.mtvapp.PlayerBundlesActivity
import com.vishnu.mtvapp.main.imagesection.imagecardpresenter.ImagePresenter
import com.vishnu.mtvapp.main.imagesection.ImagesViewModel
import com.vishnu.mtvapp.main.imagesection.model.Image
import com.vishnu.mtvapp.main.videosection.VideosListMainViewModel
import com.vishnu.mtvapp.main.videosection.model.Video
import com.vishnu.mtvapp.youtubesection.YouTubePlayerViewModel

class MainFragment : BrowseSupportFragment() {

    private lateinit var rowsAdapter: ArrayObjectAdapter
    private lateinit var videosListMainViewModel: VideosListMainViewModel
    private lateinit var imagesViewModel: ImagesViewModel
    private lateinit var youTubePlayerViewModel: YouTubePlayerViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter

        setupUIElements()
        initializeViewModels()
        loadRows()
        setupEventListeners()
    }

    private fun setupUIElements() {
        title = "My Application"
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
    }

    private fun initializeViewModels() {
        videosListMainViewModel = ViewModelProvider(this).get(VideosListMainViewModel::class.java)
        imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        youTubePlayerViewModel = ViewModelProvider(this).get(YouTubePlayerViewModel::class.java)
    }

    private fun loadRows() {
        // Videos Section
        val videosHeader = HeaderItem(0, "Videos Section")
        val videoAdapter = ArrayObjectAdapter(CardPresenter())
        videosListMainViewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            videoAdapter.clear()
            videos.forEach { video ->
                videoAdapter.add(video)
            }
        })
        rowsAdapter.add(ListRow(videosHeader, videoAdapter))

        // Images Section
        val imagesHeader = HeaderItem(1, "Images Section")
        val imagesAdapter = ArrayObjectAdapter(ImagePresenter())
        imagesViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            imagesAdapter.clear()
            images.forEach { image ->
                imagesAdapter.add(image)
            }
        })
        rowsAdapter.add(ListRow(imagesHeader, imagesAdapter))

        // YouTube Player Section
        val youTubeHeader = HeaderItem(2, "YouTube Section")
        val youTubeAdapter = ArrayObjectAdapter(CardPresenter())
//        youTubePlayerViewModel.playlist.observe(viewLifecycleOwner, Observer { playlist ->
//            youTubeAdapter.clear()
//            playlist.forEach { video ->
//                youTubeAdapter.add(video)
//            }
//        })
        rowsAdapter.add(ListRow(youTubeHeader, youTubeAdapter))
    }


    private fun setupEventListeners() {
        onItemViewClickedListener = OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is Video) {

                val video = item

                val videoDetailBundle = Bundle()
                videoDetailBundle.putString(Constants.TITLE_KEY,video.title)
                videoDetailBundle.putString(Constants.DESC_KEY,video.description)
                videoDetailBundle.putString(Constants.VIDEO_URL_KEY,video.videoUrl)
                videoDetailBundle.putString(Constants.THUMBNAIL_KEY,video.thumbnailUrl)

                val intent = Intent(requireActivity(), PlayerBundlesActivity::class.java)
                intent.putExtras(videoDetailBundle)
                startActivity(intent)
            }

            if (item is Image){

                val images = item

                val imageDetailBundle = Bundle()
                imageDetailBundle.putString(Constants.IMAGE_URL_KEY,images.imageUrl)
                val intent = Intent(requireActivity(), ImageBundleActivity::class.java)
                intent.putExtras(imageDetailBundle)
                startActivity(intent)


            }

        }
    }


}