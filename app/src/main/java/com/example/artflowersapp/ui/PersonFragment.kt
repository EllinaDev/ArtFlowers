package com.example.artflowersapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.artflowersapp.R
import com.example.artflowersapp.adapter.AccountAdapter
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.databinding.FragmentHomeBinding
import com.example.artflowersapp.databinding.FragmentPersonBinding
import com.example.artflowersapp.viewModel.AccountVM
import com.example.artflowersapp.viewModel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonFragment: Fragment(), AccountAdapter.AccountFlowerListener, AccountAdapter.AccountDeleteListener {

    private var _binding: FragmentPersonBinding? = null
    private val binding: FragmentPersonBinding get() = _binding!!
    private val adapter: AccountAdapter = AccountAdapter(this,this)
    private val viewModel: AccountVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        viewModel.flowersLiveData.observe(viewLifecycleOwner,{
            Log.d("TAG", "ALL: $it")
            adapter.submitItems(it)
        })


    }

    override fun delete(artModel: ArtModel) {
        viewModel.deleteItem(artModel)
    }

    override fun onFlowerClick(artModel: ArtModel) {
        TODO("Not yet implemented")
    }
}