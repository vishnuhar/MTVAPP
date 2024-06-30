package com.vishnu.mtvapp.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.main.imagesection.ImagesViewModel
import com.vishnu.mtvapp.main.videosection.cardpresenter.CardPresenter

class ImagesFragment : Fragment() {

    private lateinit var adapter: ArrayObjectAdapter
     lateinit var imagesViewModel: ImagesViewModel // Declare ViewModel variable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagesGridView = view.findViewById<androidx.leanback.widget.VerticalGridView>(R.id.images_grid_view)
        adapter = ArrayObjectAdapter(CardPresenter())

        imagesViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            adapter.clear()
            images.forEach { image ->
                adapter.add(image)
            }
        })

        val listRow = ListRow(HeaderItem(0, "Images"), adapter)
//        val listRowAdapter = ListRowAdapter(listRow)
//        imagesGridView.adapter = listRowAdapter
    }
}
