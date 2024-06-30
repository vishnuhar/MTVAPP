package com.vishnu.mtvapp.main.imagesection.displayimage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.vishnu.mtvapp.R



class ImageDisplayFragment : Fragment() {
    private lateinit var imageDisplayViewModel: ImageDisplayViewModel
    private lateinit var imageView:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        imageDisplayViewModel = ViewModelProvider(this).get(ImageDisplayViewModel::class.java)
        loadBundleValue()
//

        return inflater.inflate(R.layout.fragment_image_display, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.imageView)
        imageDisplayViewModel.detailData.observe(viewLifecycleOwner) { imageUrl ->
            if (imageUrl != null) {
                Glide.with(imageView.context)
                    .load(imageUrl.imageUrl)
                    .centerCrop()
                    .error(R.drawable.movie)
                    .into(imageView)
            }

        }


    }


    private fun loadBundleValue() {
        val detailsBundle = arguments
        if (detailsBundle !=null){
            imageDisplayViewModel.loadDetailImageData(detailsBundle)
        }
    }

}



