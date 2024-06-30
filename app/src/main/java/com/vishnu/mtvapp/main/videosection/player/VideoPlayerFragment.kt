package com.vishnu.mtvapp.main.videosection.player

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.widget.SeekBar

import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener

import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.Util
import com.vishnu.mtvapp.R


class VideoPlayerFragment : Fragment() {
    private lateinit var viewModel: VideoPlayerBundleViewModel
    private var playerView: PlayerView? = null
    private var player: SimpleExoPlayer? = null

    private var btnRewind: ImageButton? = null
    private var btnPlayPause: ImageButton? = null
    private var btnForward: ImageButton? = null
    private var seekBar: SeekBar? = null

    private var isPlaying = false
    private var isSeeking = false



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
        btnRewind = view.findViewById(R.id.btn_rewind)
        btnPlayPause = view.findViewById(R.id.btn_play_pause)
        btnForward = view.findViewById(R.id.btn_forward)
        seekBar = view.findViewById(R.id.seek_bar)

        // Initialize the ExoPlayer
        initializePlayer()

        // Set click listeners for custom controls
        btnRewind!!.setOnClickListener {
            rewind()
        }

        btnPlayPause!!.setOnClickListener {
            togglePlayPause()
        }

        btnForward!!.setOnClickListener {
            forward()
        }

        // Set seek bar listener
        seekBar!!.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update seek position only when user interacts with seek bar
                if (fromUser) {
                    seekTo(progress.toLong())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeeking = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeeking = false
            }
        })

        // Update seek bar position as video progresses
        player?.addAnalyticsListener(object :
            com.google.android.exoplayer2.analytics.AnalyticsListener {
            override fun onPositionDiscontinuity(
                eventTime: AnalyticsListener.EventTime,
                reason: Int
            ) {
                if (!isSeeking) {
                    seekBar!!.progress = player?.currentPosition?.toInt() ?: 0
                }
            }
        })
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
            playerView!!.player = player


            viewModel.detailData.observe(viewLifecycleOwner,{url->

            val uri = Uri.parse(url.videoUrl)
            val mediaItem = MediaItem.fromUri(uri)
            player?.setMediaItem(mediaItem)
            })


            player?.prepare()
            player?.play()

            // Set initial play/pause button state
            isPlaying = true
            btnPlayPause!!.setImageResource(R.drawable.ic_baseline_pause_24) // Assuming you have an icon for pause

            // Set seek bar max duration
            player?.duration?.let {
                seekBar!!.max = it.toInt()
            }
        }
    }

    private fun releasePlayer() {
        player?.release()
        player = null
    }

    private fun rewind() {
        player?.let {
            val position = it.currentPosition
            it.seekTo(Math.max(position - 1000, 0))
        }
    }

    private fun forward() {
        player?.let {
            val position = it.currentPosition
            val duration = it.duration
            it.seekTo(Math.min(position + 100, duration))
        }
    }

    private fun togglePlayPause() {
        if (player != null) {
            if (isPlaying) {
                player?.pause()
                btnPlayPause!!.setImageResource(R.drawable.baseline_play_arrow_24) // Set play icon
            } else {
                player?.play()
                btnPlayPause!!.setImageResource(R.drawable.ic_baseline_pause_24) // Set pause icon
            }
            isPlaying = !isPlaying
        }
    }

    private fun seekTo(positionMs: Long) {
        player?.seekTo(positionMs)
    }
}