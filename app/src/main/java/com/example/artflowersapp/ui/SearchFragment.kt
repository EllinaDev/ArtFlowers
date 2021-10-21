package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.artflowersapp.adapter.HomeAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.FragmentSearchBinding
import com.example.artflowersapp.viewModel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment(), HomeAdapter.FlowerBasketListener, HomeAdapter.FlowerListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!
    private val adapter: HomeAdapter = HomeAdapter(this,this)
    private val viewModel: ArtViewModel by viewModels()

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
            val hasText = text?.trim()?.isNotEmpty() == true

            if (hasText) {
                viewModel.search("%$text%")
                binding.tvResultToSearch.text = text
                println(text)
                binding.con.isVisible = hasText
            }
            binding.con.isVisible = hasText
            binding.ivSearchBg.isVisible = !hasText
        }


    }

    companion object{
        fun newInstance() = SearchFragment()
    }

    override fun onFlowerClick(flowers: ArtModel) {
        val direction = SearchFragmentDirections.actionSearchFragmentToDetailFragment(flowers)
        findNavController().navigate(direction)
    }

    override fun onBasketClick(flowers: ArtModel) {
        viewModel.addFlowerToBasket(flowers)
    }




}