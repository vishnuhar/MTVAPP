package com.vishnu.mtvapp.main.videosection.player

import android.content.Context
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.vishnu.mtvapp.R


class VideoPlayerFragment : Fragment() {
    private lateinit var playerView: PlayerView
    private var player: SimpleExoPlayer? = null

    private lateinit var viewModel: VideoPlayerBundleViewModel
    private lateinit var tvTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_video_player, container, false)

        viewModel = ViewModelProvider(this).get(VideoPlayerBundleViewModel::class.java)
        loadBundleValue()


        return rootView
    }


    private fun loadBundleValue() {
        val detailsBundle = arguments
        if (detailsBundle !=null){
            viewModel.loadDetailData(detailsBundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView = view.findViewById(R.id.player_view)

        tvTitle = view.findViewById(R.id.tv_title)


        initializePlayer()
        observeTitle()
        titleVisible()

    }

    private  fun observeTitle(){
        viewModel.detailData.observe(viewLifecycleOwner) { title->
            tvTitle.text = title.title

        }
    }

 private fun titleVisible(){
        playerView.setControllerVisibilityListener { visibility ->
            if (visibility == View.VISIBLE) {
                tvTitle.visibility = View.VISIBLE
            } else {
                tvTitle.visibility = View.GONE
            }
        }
    }



    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {
        if (player == null) {
            player = SimpleExoPlayer.Builder(requireContext()).build()
            playerView.player = player


        viewModel.detailData.observe(viewLifecycleOwner) { url ->

            val uri = Uri.parse(url.videoUrl)
            val mediaItem = MediaItem.fromUri(uri)
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.play()
        }

        }
    }


    private fun releasePlayer() {
        player?.release()
        player = null

    }

}