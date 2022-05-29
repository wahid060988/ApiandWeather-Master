package com.assignment.demo.sampleapp.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.demo.sampleapp.base.BaseApiResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.PeopleResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.RandomUserResponseModel
import com.assignment.demo.sampleapp.db.model.UserRoomDataModel
import com.assignment.demo.sampleapp.db.room.DatabaseHelper
import com.assignment.demo.sampleapp.repository.ApiRepository
import com.assignment.demo.sampleapp.utils.DataConverter
import com.assignment.demo.sampleapp.utils.LiveDataUtils
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel(
  private val apiRepository: ApiRepository,
  private val dbHelper: DatabaseHelper,
  private val dataConverter: DataConverter
) : ViewModel() {

  private val peopleResponseMutableLiveData: MutableLiveData<List<UserRoomDataModel>> =
    MutableLiveData()

  fun getPeopleResponseMutableLiveData(): MutableLiveData<List<UserRoomDataModel>> {
    return peopleResponseMutableLiveData
  }

  fun searchPeoplesResponseLiveData(searchQuery: String) {
    /*val baseSearchApiResponseModelSingle: Single<RandomUserResponseModel?>? =
      apiRepository.search(searchQuery)*/
    //    val dataFromDB = dbHelper.fetchSearchData("%"+searchQuery+"%")
    //    LiveDataUtils.updateStatus(peopleResponseMutableLiveData, baseSearchApiResponseModelSingle)

    GlobalScope.launch(Dispatchers.IO) {
      try {
        val dataFromDB = dbHelper.fetchSearchData("%$searchQuery%")
        peopleResponseMutableLiveData.postValue(dataFromDB)
      } catch (e: Exception) {
        e.printStackTrace()
        Log.d("Irshad", "Insert Exception :::: " + e.localizedMessage)
      }
    }
  }

  /*fun filterData(it: Any, dataConverter: DataConverter): ArrayList<RandomUserResponseModel> {
      return dataConverter.convertToSearchModelList(it as PeopleResponseModel)
  }*/

  private val remoteResponseRandomUserMutableLiveData: MutableLiveData<BaseApiResponseModel<RandomUserResponseModel>> =
    MutableLiveData()


  fun getRemoteResponseRandomUserMutableLiveData(): MutableLiveData<BaseApiResponseModel<RandomUserResponseModel>> {
    return remoteResponseRandomUserMutableLiveData
  }

  fun getRandomUserList() {
    val baseSearchApiResponseModelSingle: Single<RandomUserResponseModel>? =
      apiRepository.randomUserList()
    LiveDataUtils.updateStatus(
      remoteResponseRandomUserMutableLiveData,
      baseSearchApiResponseModelSingle
    )
  }

  private val loadMoreMutableLiveData: MutableLiveData<BaseApiResponseModel<PeopleResponseModel>> =
    MutableLiveData()

  fun getLoadMoreMutableLiveData(): MutableLiveData<BaseApiResponseModel<PeopleResponseModel>> {
    return loadMoreMutableLiveData
  }

  fun getPeopleLoadMore(url: String?) {
    val baseSearchApiResponseModelSingle: Single<PeopleResponseModel>? =
      apiRepository.loadMore(url)
    LiveDataUtils.updateStatus(loadMoreMutableLiveData, baseSearchApiResponseModelSingle)
  }

  private suspend fun saveRandomUserDataInDb(data: RandomUserResponseModel) {
    val dbData = dataConverter.transformApiDataToDbData(data)
    Log.d("Irshad", "dbData :::: " + dbData.size)
    dbHelper.insert(dbData)
  }

  fun saveData(data: RandomUserResponseModel) {
    GlobalScope.launch(Dispatchers.IO) {
      try {
        saveRandomUserDataInDb(data)
      } catch (e: Exception) {
        e.printStackTrace()
        Log.d("Irshad", "Insert Exception :::: " + e.localizedMessage)
      }
    }
  }

  fun getDBData() {
    GlobalScope.launch(Dispatchers.IO) {
      try {
        val dataFromDB = dbHelper.fetchAllData()
        Log.d("Irshad", "DataCount :::: " + dataFromDB.size)
      } catch (e: Exception) {
        e.printStackTrace()
        Log.d("Irshad", "Fetch Exception :::: " + e.localizedMessage)
      }
    }

  }


}