package com.example.artflowersapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val repository: ArtRepository): ViewModel() {

    val flowersLiveData: LiveData<ArtModel> = MutableLiveData()

    fun insertName(artModel: ArtModel) = viewModelScope.launch {
        repository.insertName(artModel)
    }

    fun selectAll(artModel: List<ArtModel>) = viewModelScope.launch {
        repository.selectAll()
    }

}