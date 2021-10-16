package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.artflowersapp.R
import com.example.artflowersapp.databinding.FragmentPersonBinding
import com.example.artflowersapp.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsAccountFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvLanguage.setOnClickListener{
            it.findNavController().navigate(R.id.languageFragment)
        }
        binding.tvCurrency.setOnClickListener{
            it.findNavController().navigate(R.id.currencyFragment)
        }
        binding.tvThemas.setOnClickListener{
            it.findNavController().navigate(R.id.themaFragment)
        }
    }
}