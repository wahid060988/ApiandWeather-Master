package com.assignment.demo.starwarsapp.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.demo.starwarsapp.db.room.DatabaseHelper
import com.assignment.demo.starwarsapp.home.ApiRepository
import com.assignment.demo.starwarsapp.utils.DataConverter

class DetailViewModelFactory(
    private val apiRepository: ApiRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(apiRepository) as T
    }
}