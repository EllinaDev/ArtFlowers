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
class SearchVM @Inject constructor(
    private val repository: ArtRepository
) : ViewModel(){

    private val _searchResult = MutableLiveData<List<ArtModel>>()
    val searchResult : LiveData<List<ArtModel>> = _searchResult


    fun search(query: String) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _searchResult.postValue(repository.search(query))
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}