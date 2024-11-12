package com.example.lab8_api.ui

import android.view.View
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8_api.R
import kotlin.properties.ReadOnlyProperty

class PhotoFragment : Fragment(R.layout.fragment_photo_list) {
    private val viewModel: PhotoViewModel by viewModels()

    private fun viewModels(): ReadOnlyProperty<PhotoFragment, PhotoViewModel> {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = PhotoAdapter()
        recyclerView.adapter = adapter

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            photos?.let {
                adapter.submitList(it)
            }
        }
    }
}
