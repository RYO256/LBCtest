package com.example.lbctest.ui.albumslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lbctest.R
import com.example.lbctest.core.Resource
import com.example.lbctest.databinding.FragmentAlbumsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumsListFragment : Fragment() {

    private val viewModel by activityViewModels<AlbumsListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_albums_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAlbumsListBinding.bind(view)

        val albumAdapter = AlbumAdapter() {
            findNavController().navigate(AlbumsListFragmentDirections.actionAlbumsListFragment2ToAlbumDetailFragment(it))
        }
        albumAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        val columns = resources.getInteger(R.integer.albums_columns)
        binding.albumsListAlbumsRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.albumsListAlbumsRecycler.adapter = albumAdapter

        viewModel.getAlbums().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    albumAdapter.submitList(result.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Unable to fetch data ... ", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}