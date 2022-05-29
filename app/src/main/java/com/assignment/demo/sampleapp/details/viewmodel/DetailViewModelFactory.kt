package com.assignment.demo.sampleapp.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.demo.sampleapp.repository.ApiRepository

class DetailViewModelFactory(
    private val apiRepository: ApiRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(apiRepository) as T
    }
}