package com.example.lbctest.ui.albumdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lbctest.R
import com.example.lbctest.databinding.FragmentAlbumsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_albums_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAlbumsDetailsBinding.bind(view)

        val args: AlbumDetailFragmentArgs by navArgs()

        binding.albumsDetailsAlbumTitle.text = "Album : ${args.album.id} "
        val songAdapter = SongAdapter(){
            Toast.makeText(requireContext(), "This click will be handled soon ... ", Toast.LENGTH_LONG).show()
        }
        songAdapter.stateRestorationPolicy= RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        val columns = resources.getInteger(R.integer.songs_columns)
        binding.albumsDetailsSongsRecycler.layoutManager = GridLayoutManager(requireContext(), columns)
        binding.albumsDetailsSongsRecycler.adapter = songAdapter

        songAdapter.submitList(args.album.songs)

    }

}