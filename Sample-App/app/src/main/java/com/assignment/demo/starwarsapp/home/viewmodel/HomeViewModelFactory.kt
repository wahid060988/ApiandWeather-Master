package com.assignment.demo.starwarsapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.demo.starwarsapp.db.room.DatabaseHelper
import com.assignment.demo.starwarsapp.home.ApiRepository
import com.assignment.demo.starwarsapp.utils.DataConverter

class HomeViewModelFactory(
    private val apiRepository: ApiRepository,
    private val dbHelper: DatabaseHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiRepository, dbHelper) as T
    }
}