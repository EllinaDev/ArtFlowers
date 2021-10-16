package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.artflowersapp.R
import com.example.artflowersapp.adapter.HomeAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.FragmentHomeBinding
import com.example.artflowersapp.utils.FakeData
import com.example.artflowersapp.viewModel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAdapter.FlowerListener, HomeAdapter.FlowerBasketListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val viewModel: ArtViewModel by viewModels()
    private val adapter: HomeAdapter = HomeAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.flowersLiveData.observe(viewLifecycleOwner,{
            adapter.submitItems(it)
        })
        searchHome()

    }

    override fun onFlowerClick(flowers: ArtModel) {
        val direction = HomeFragmentDirections.actionHomeFragmentToDetailFragment(flowers)
        findNavController().navigate(direction)
    }

    override fun onBasketClick(flowers: ArtModel) {
        viewModel.addFlowerToBasket(flowers)
    }

    private fun searchHome(){
        binding.etSearch.setOnClickListener(searchListener())
    }

    private fun searchListener(): View.OnClickListener {
        return View.OnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }
    }

}


