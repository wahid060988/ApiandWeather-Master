package com.assignment.demo.sampleapp.details.viewmodel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.demo.sampleapp.base.BaseApiResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.Results
import com.assignment.demo.sampleapp.repository.ApiRepository
import com.assignment.demo.sampleapp.utils.LiveDataUtils
import io.reactivex.rxjava3.core.Single

class DetailsViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    private val peopleResultsMutableLiveData: MutableLiveData<BaseApiResponseModel<Results>> =
        MutableLiveData()
}