package com.example.artflowersapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artflowersapp.data.BasketModel
import com.example.artflowersapp.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketVM @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

    val basketList: LiveData<List<BasketModel>> get() = repository.getAllFlowersFromBasket()

    fun deleteFromBasket(artModel: BasketModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFromBasket(artModel)
        }
    }
}