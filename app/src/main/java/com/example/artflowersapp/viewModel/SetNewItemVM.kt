package com.example.artflowersapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artflowersapp.data.ArtModel
import com.example.artflowersapp.repository.ArtRepository
import com.example.artflowersapp.repository.SetNewItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetNewItemVM @Inject constructor(private val repository: SetNewItemRepository) : ViewModel() {

    fun insertNewItem(artModel: ArtModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNewItem(artModel)
    }

}