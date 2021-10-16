package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.artflowersapp.adapter.HomeAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.FragmentHomeBinding
import com.example.artflowersapp.databinding.FragmentSearchBinding
import com.example.artflowersapp.utils.FakeData
import com.example.artflowersapp.viewModel.SearchVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment(), HomeAdapter.FlowerBasketListener, HomeAdapter.FlowerListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val adapter: HomeAdapter = HomeAdapter(this,this)
    private val viewModel: SearchVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.searchResult.observe(viewLifecycleOwner,{
            adapter.submitItems(it)
            println("Search result: $it")
        })

        binding.etSearch.doOnTextChanged { text, start, before, count ->
            if (text?.trim()?.isNotEmpty() == true) {
                viewModel.search("%$text%")
                println(text)
            }
        }


    }

    companion object{
        fun newInstance() = SearchFragment()
    }

    override fun onFlowerClick(flowers: ArtModel) {
        TODO("Not yet implemented")
    }

    override fun onBasketClick(flowers: ArtModel) {
        TODO("Not yet implemented")
    }
}