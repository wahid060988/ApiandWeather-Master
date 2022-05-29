package com.assignment.demo.sampleapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.demo.sampleapp.db.room.DatabaseHelper
import com.assignment.demo.sampleapp.repository.ApiRepository
import com.assignment.demo.sampleapp.utils.DataConverter

class HomeViewModelFactory(
  private val apiRepository: ApiRepository,
  private val dbHelper: DatabaseHelper,
  private val dataConverter: DataConverter
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return HomeViewModel(apiRepository, dbHelper, dataConverter) as T
  }
}