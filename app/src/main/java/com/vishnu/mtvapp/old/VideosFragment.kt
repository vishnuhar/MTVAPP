package com.vishnu.mtvapp.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.VerticalGridView
import androidx.lifecycle.Observer
import com.vishnu.mtvapp.R
import com.vishnu.mtvapp.main.videosection.VideosListMainViewModel
import com.vishnu.mtvapp.main.videosection.cardpresenter.CardPresenter

class VideosFragment : Fragment() {

    lateinit var viewModel: VideosListMainViewModel // Assuming this is injected or set externally

    private lateinit var adapter: ArrayObjectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val videosGridView = view.findViewById<VerticalGridView>(R.id.videos_grid_view)
        adapter = ArrayObjectAdapter(CardPresenter())

        // Observe videos list from ViewModel
        viewModel.videos.observe(viewLifecycleOwner, Observer { videos ->
            adapter.clear()
            videos.forEach { video ->
                adapter.add(video)
            }
        })

//        videosGridView.adapter = adapter
    }
}
