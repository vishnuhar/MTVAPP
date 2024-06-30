package com.vishnu.mtvapp.main.youtubesection

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.utils.Constants

class YoutubePlay : FragmentActivity() {
    private lateinit var viewModelYTubePlayer: YouTubePlayerActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_play)

        val webView: WebView = findViewById(R.id.webView)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        viewModelYTubePlayer = ViewModelProvider(this).get(YouTubePlayerActivityViewModel::class.java)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }


        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {

                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                title?.let { setTitle(it) }
                super.onReceivedTitle(view, title)
            }

            override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
                super.onReceivedIcon(view, icon)
            }
        }

        viewModelYTubePlayer.videoUrl.observe(this) { url ->
            webView.loadUrl(url)
        }

        val bundle = intent.extras
        val videoUrl = bundle!!.getString(Constants.YOUTUBE_URL_KEY)
        viewModelYTubePlayer.setVideoUrl(videoUrl.toString())
    }
}
