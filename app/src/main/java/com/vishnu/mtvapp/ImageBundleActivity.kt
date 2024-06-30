package com.vishnu.mtvapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.vishnu.mtvapp.main.imagesection.displayimage.ImageDisplayFragment

class ImageBundleActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_bundle)

        if (savedInstanceState == null){
            val bundle = intent.extras
            val imageDisplayFragment = ImageDisplayFragment()
           imageDisplayFragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.image_browse_fragment, imageDisplayFragment)
                .commitNow()
        }
    }
}