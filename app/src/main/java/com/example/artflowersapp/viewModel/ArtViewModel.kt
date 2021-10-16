package com.example.artflowersapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(private val repository: ArtRepository): ViewModel() {

    val flowersLiveData: LiveData<List<ArtModel>> = repository.selectAll()



    fun addFlowerToBasket(artModel: ArtModel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.addFlowerIntoBasket(artModel)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun addBaseDataToRoom(flowers: List<ArtModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addBaseDataToRoom(flowers)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

}