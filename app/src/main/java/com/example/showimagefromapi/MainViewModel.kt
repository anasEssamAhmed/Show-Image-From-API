package com.example.showimagefromapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showimagefromapi.model.ImagePost
import com.example.showimagefromapi.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _response = MutableStateFlow<Response<List<ImagePost>>>(Response.success(mutableListOf()))
    val response = _response.asStateFlow()

    // Get Image From Api
    fun getImagePost(){
        viewModelScope.launch {
            val response = repository.getImagePost()
            _response.value = response
        }
    }
}