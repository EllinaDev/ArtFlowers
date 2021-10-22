package com.example.artflowersapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(private val repository: ArtRepository) : ViewModel() {

    fun insertAll(artModel: List<ArtModel>) = viewModelScope.launch(Dispatchers.IO){
        repository.insertAll(artModel)
    }

}