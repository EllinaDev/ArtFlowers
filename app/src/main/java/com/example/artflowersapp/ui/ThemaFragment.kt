package com.example.artflowersapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artflowersapp.databinding.FragmentLanguageBinding
import com.example.artflowersapp.databinding.FragmentThemaBinding

class ThemaFragment : Fragment() {
    private var _binding: FragmentThemaBinding? = null
    private val binding: FragmentThemaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThemaBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}