package com.vishnu.mtvapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vishnu.mtvapp.main.videosection.player.VideoPlayerFragment

class PlayerBundlesActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_bundle_screen)

        if (savedInstanceState == null){
            val bundle = intent.extras
            val detailFragment = VideoPlayerFragment()
            detailFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_browse_fragment, detailFragment)
                .commitNow()
        }
    }
}