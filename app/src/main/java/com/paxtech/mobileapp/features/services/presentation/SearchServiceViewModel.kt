package com.paxtech.mobileapp.features.services.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paxtech.mobileapp.features.services.domain.Service
import com.paxtech.mobileapp.features.services.domain.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchServiceViewModel @Inject constructor(private val repository: ServiceRepository): ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services

    fun onChangeQuery(value: String){
        _query.value = value
    }

    fun searchService() {
        viewModelScope.launch {
            _services.value = repository.searchService(_query.value)
        }
    }
}