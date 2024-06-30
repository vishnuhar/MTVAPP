package com.vishnu.mtvapp.old

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.main.MainFragment
import com.vishnu.mtvapp.main.videosection.player.VideoPlayerBundleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.concurrent.TimeUnit

class VideoDetailFragment : Fragment(), SurfaceHolder.Callback {
    private var titleNameTxt:TextView? = null
    private var descNameTxt: TextView? = null
    private var videoUrlKey: TextView? = null
    private lateinit var videoSurface: SurfaceView
    private lateinit var playButton: ImageButton
    private lateinit var rewindButton: ImageButton
    private lateinit var fastForwardButton: ImageButton
    private lateinit var volumeButton: ImageButton
    private lateinit var fullscreenButton: ImageButton
    private lateinit var playbackSeekBar: SeekBar
    private lateinit var videoTitle: TextView
    private lateinit var videoDescription: TextView
    private lateinit var playTime: TextView
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false
    private var isFullscreen = false
    private val mHandler = Handler()
    private lateinit var viewModel: VideoPlayerBundleViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_video_detail, container, false)

        videoSurface = rootView.findViewById(R.id.video_surface)
        playButton = rootView.findViewById(R.id.play_button)
        rewindButton = rootView.findViewById(R.id.rewind_button)
        fastForwardButton = rootView.findViewById(R.id.fast_forward_button)
        volumeButton = rootView.findViewById(R.id.volume_button)
        fullscreenButton = rootView.findViewById(R.id.fullscreen_button)
        playbackSeekBar = rootView.findViewById(R.id.playback_seekbar)
        videoTitle = rootView.findViewById(R.id.video_title)
        videoDescription = rootView.findViewById(R.id.video_description)
        playTime = rootView.findViewById(R.id.play_time)

        setupViewModel()
//        setupVideoPlayer()
//        loadBundleValue()

        return rootView
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(VideoPlayerBundleViewModel::class.java)

//
//        viewModel.videoDetail.observe(viewLifecycleOwner, { videoDetail ->
////            videoTitle.text = videoDetail.title
////            videoDescription.text = videoDetail.description
////            initializeMediaPlayer(videoDetail.videoUrl)
//        })



    }

//    private fun loadBundleValue() {
//        val detailsBundle = arguments
//        if (detailsBundle !=null){
//            viewModel.loadDetailData(detailsBundle)
//        }
//    }



    private fun setupVideoPlayer() {
        videoSurface.holder.addCallback(this)
        playButton.setOnClickListener { togglePlayPause() }
        rewindButton.setOnClickListener { rewind() }
        fastForwardButton.setOnClickListener { fastForward() }
//        volumeButton.setOnClickListener { toggleMute() }
        fullscreenButton.setOnClickListener { toggleFullscreen() }
    }

    private fun initializeMediaPlayer(videoUrl: String) {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDisplay(videoSurface.holder)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                mediaPlayer.setDataSource(requireContext(), Uri.parse(videoUrl))
                mediaPlayer.prepareAsync()
                mediaPlayer.setOnPreparedListener {
                    playbackSeekBar.max = mediaPlayer.duration
                    updateSeekBar()
                    updatePlayTime()
                    mediaPlayer.start()
                    isPlaying = true
                    playButton.setImageResource(R.drawable.ic_baseline_pause_24) // Change to pause icon
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun onBackPressed(): Boolean {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
            playButton.setImageResource(R.drawable.baseline_play_arrow_24) // Change to play icon
            return true
        } else {

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commit()
            return true
        }
    }
    private fun togglePlayPause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            isPlaying = false
            playButton.setImageResource(R.drawable.baseline_play_arrow_24) // Change to play icon
        } else {
            mediaPlayer.start()
            isPlaying = true
            playButton.setImageResource(R.drawable.ic_baseline_pause_24) // Change to pause icon
        }
    }

    private fun rewind() {
        val currentPosition = mediaPlayer.currentPosition
        val rewindPosition = currentPosition - 10000 // Rewind 10 seconds
        mediaPlayer.seekTo(rewindPosition.coerceAtLeast(0))
    }

    private fun fastForward() {
        val currentPosition = mediaPlayer.currentPosition
        val forwardPosition = currentPosition + 10000 // Fast forward 10 seconds
        mediaPlayer.seekTo(forwardPosition.coerceAtMost(mediaPlayer.duration))
    }

    private fun toggleMute() {
//        val isMuted = mediaPlayer.isMuted
//        mediaPlayer.isMuted = !isMuted
//        volumeButton.setImageResource(if (isMuted) R.drawable.ic_baseline_volume_down_24 else R.drawable.ic_volume_off)
    }

    private fun toggleFullscreen() {
        // Implement fullscreen functionality
        // Example: Update layout to fullscreen mode and handle orientation changes
        // For simplicity, toggle fullscreen mode state here
        isFullscreen = !isFullscreen
        if (isFullscreen) {
            // Expand layout to fullscreen
        } else {
            // Return to normal layout size
        }
    }

    private fun updateSeekBar() {
        activity?.runOnUiThread {
            if (mediaPlayer.isPlaying) {
                val currentPosition = mediaPlayer.currentPosition
                playbackSeekBar.progress = currentPosition
                mHandler.postDelayed({ updateSeekBar() }, 1000) // Update seek bar every second
            }
        }
    }

    private fun updatePlayTime() {
        activity?.runOnUiThread {
            val currentPosition = mediaPlayer.currentPosition
            val totalDuration = mediaPlayer.duration
            val currentTime = String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(currentPosition.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(currentPosition.toLong()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentPosition.toLong()))
            )
            val totalTime = String.format(
                "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(totalDuration.toLong()),
                TimeUnit.MILLISECONDS.toSeconds(totalDuration.toLong()) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalDuration.toLong()))
            )
            playTime.text = "$currentTime / $totalTime"
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        // SurfaceHolder Callback
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // SurfaceHolder Callbackx
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        // SurfaceHolder Callback
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }


}