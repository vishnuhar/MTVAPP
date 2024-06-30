package com.vishnu.mtvapp.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager

import com.bumptech.glide.request.transition.Transition
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.vishnu.mtvapp.ImageBundleActivity
import com.vishnu.mtvapp.main.videosection.cardpresenter.CardPresenter
import com.vishnu.mtvapp.utils.Constants
import com.vishnu.mtvapp.PlayerBundlesActivity
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.main.imagesection.imagecardpresenter.ImagePresenter
import com.vishnu.mtvapp.main.imagesection.ImagesViewModel
import com.vishnu.mtvapp.main.imagesection.model.Image
import com.vishnu.mtvapp.main.videosection.VideosListMainViewModel
import com.vishnu.mtvapp.main.videosection.model.Video
import com.vishnu.mtvapp.youtubesection.YouTubePlayerViewModel
import com.vishnu.mtvapp.youtubesection.YoutubeCardPresenter
import com.vishnu.mtvapp.youtubesection.model.YouTube

class MainFragment : BrowseSupportFragment() {

    private lateinit var rowsAdapter: ArrayObjectAdapter
    private lateinit var videosListMainViewModel: VideosListMainViewModel
    private lateinit var imagesViewModel: ImagesViewModel
    private lateinit var youTubePlayerViewModel: YouTubePlayerViewModel
    private lateinit var backgroundManager: BackgroundManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter

        backgroundManager = BackgroundManager.getInstance(requireActivity())
        backgroundManager.attach(requireActivity().window)


        setupUIElements()
        initializeViewModels()
        loadRows()
        setupEventListeners()
        updateItemBackground()
    }


    private fun setupUIElements() {
        title = getString(R.string.mtv)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireContext(), R.color.default_background)
    }

    private fun initializeViewModels() {
        videosListMainViewModel = ViewModelProvider(this).get(VideosListMainViewModel::class.java)
        imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        youTubePlayerViewModel = ViewModelProvider(this).get(YouTubePlayerViewModel::class.java)
    }

    private fun loadRows() {

        val videosHeader = HeaderItem(0, getString(R.string.videos_section))
        val videoAdapter = ArrayObjectAdapter(CardPresenter())
        videosListMainViewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            videoAdapter.clear()
            videos.forEach { video ->
                videoAdapter.add(video)
            }
        })
        rowsAdapter.add(ListRow(videosHeader, videoAdapter))



        val imagesHeader = HeaderItem(1, getString(R.string.images_section))
        val imagesAdapter = ArrayObjectAdapter(ImagePresenter())
        imagesViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            imagesAdapter.clear()
            images.forEach { image ->
                imagesAdapter.add(image)
            }
        })
        rowsAdapter.add(ListRow(imagesHeader, imagesAdapter))


        val youTubeHeader = HeaderItem(2, getString(R.string.youtube_section))

        val youTubeAdapter = ArrayObjectAdapter(YoutubeCardPresenter())
        youTubePlayerViewModel.youTube.observe(viewLifecycleOwner, Observer { playlist ->
            youTubeAdapter.clear()
            playlist.forEach { video ->
                youTubeAdapter.add(video)
            }
        })


        rowsAdapter.add(ListRow(youTubeHeader, youTubeAdapter))
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundManager.release()
    }

     private fun updateBackground(uri: String?) {
        Glide.with(requireContext())
            .load(uri)
            .centerCrop()
            .into(object : SimpleTarget<Drawable>() {
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    backgroundManager.drawable = resource
                }
            })


    }

    private fun updateItemBackground(){
        onItemViewSelectedListener = OnItemViewSelectedListener { _, item, _, row ->
            if (item is Video) {
                updateBackground(item.backgroundUrl)
            }

            if (item is Image){
                updateBackground(item.imageUrl)
            }

            if (item is YouTube){
                updateBackground(item.backgroundUrl)
            }
        }
    }
    private fun setupEventListeners() {



        onItemViewClickedListener =
            OnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
                if (item is Video) {

                    val video = item

                    val videoDetailBundle = Bundle()
                    videoDetailBundle.putString(Constants.TITLE_KEY, video.title)
                    videoDetailBundle.putString(Constants.DESC_KEY, video.description)
                    videoDetailBundle.putString(Constants.VIDEO_URL_KEY, video.videoUrl)
                    videoDetailBundle.putString(Constants.THUMBNAIL_KEY, video.thumbnailUrl)

                    val intent = Intent(requireActivity(), PlayerBundlesActivity::class.java)
                    intent.putExtras(videoDetailBundle)
                    startActivity(intent)
                }

                if (item is Image) {

                    val images = item

                    val imageDetailBundle = Bundle()
                    imageDetailBundle.putString(Constants.IMAGE_URL_KEY, images.imageUrl)
                    val intent = Intent(requireActivity(), ImageBundleActivity::class.java)
                    intent.putExtras(imageDetailBundle)
                    startActivity(intent)


                }

                if (item is YouTube) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.videoId))
                    startActivity(intent)
                }


            }

    }

}