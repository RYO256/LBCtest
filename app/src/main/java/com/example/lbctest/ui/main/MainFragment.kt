package com.example.lbctest.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.lbctest.R
import com.example.lbctest.core.Resource
import com.example.lbctest.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel>()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = MainFragmentBinding.bind(view)

        viewModel.getAlbums().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.message.text = "empty"
                    }
                    binding.message.text = result.data.toString()
                }
                is Resource.Failure -> {
                    binding.message.text = "Failure"
                }
            }
        })
    }

}