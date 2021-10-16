package com.example.artflowersapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.data.BasketModel
import com.example.artflowersapp.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountVM @Inject constructor(private val repository: ArtRepository) : ViewModel() {

    val flowersLiveData: LiveData<List<ArtModel>> = repository.selectAll()

    fun deleteItem(artModel: ArtModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(artModel)
        }
    }

}