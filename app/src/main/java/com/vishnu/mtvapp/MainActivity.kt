package com.vishnu.mtvapp

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.vishnu.mtvapp.main.MainFragment

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }

//    override fun onBackPressed() {
//        val fragment = supportFragmentManager.findFragmentById(R.id.main_browse_fragment)
//        if (fragment is VideoDetailFragment) {
//            if (!fragment.onBackPressed()) {
//                super.onBackPressed()
//            }
//        } else {
//            super.onBackPressed()
//        }
//    }
}