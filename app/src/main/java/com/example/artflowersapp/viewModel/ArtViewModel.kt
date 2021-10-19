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

    private val _similarItemsLD = MutableLiveData<List<ArtModel>>()
    val similarItemsLD : LiveData<List<ArtModel>> = _similarItemsLD

    private val _searchResult = MutableLiveData<List<ArtModel>>()
    val searchResult : LiveData<List<ArtModel>> = _searchResult



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

    fun getSimilarQuery( query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _similarItemsLD.postValue(repository.getSimilarQuery(query))
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

    fun search(query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _searchResult.postValue(repository.search(query))
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

    fun updateLikes(artModel: ArtModel?) = viewModelScope. launch(Dispatchers.IO) {
        try {
            if (artModel != null) {
                repository.updateLikes(artModel)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}