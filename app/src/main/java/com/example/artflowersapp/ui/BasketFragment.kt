package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.artflowersapp.R
import com.example.artflowersapp.adapter.BasketAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.data.BasketModel
import com.example.artflowersapp.databinding.FragmentBasketBinding
import com.example.artflowersapp.viewModel.BasketVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment: Fragment(), BasketAdapter.BasketListener, BasketAdapter.FlowerListener {

    private var _binding: FragmentBasketBinding? = null
    private val binding: FragmentBasketBinding get() = _binding!!
    private val adapter: BasketAdapter = BasketAdapter(this,this)

    private val vm: BasketVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketBinding.inflate(inflater)
        return binding.root
        searchHome()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        vm.basketList.observe(viewLifecycleOwner, {
            adapter.submitItems(it)
        })
    }

    override fun deleteFromBasket(basketModel: BasketModel) {
        vm.deleteFromBasket(basketModel)
    }

    override fun onFlowerClick(flowers: ArtModel) {
        val direction = BasketFragmentDirections.actionBasketFragmentToDetailFragment(flowers)
        findNavController().navigate(direction)
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