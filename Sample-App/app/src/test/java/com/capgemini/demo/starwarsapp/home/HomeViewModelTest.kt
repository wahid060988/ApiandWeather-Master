package com.assignment.demo.starwarsapp.home

import com.assignment.demo.starwarsapp.db.room.DatabaseHelper
import com.assignment.demo.starwarsapp.factory.MockDataFactory
import com.assignment.demo.starwarsapp.home.viewmodel.HomeViewModel
import com.assignment.demo.starwarsapp.utils.DataConverter
import com.assignment.demo.starwarsapp.utils.InstantExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.core.Single
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
class HomeViewModelTest {

    lateinit var viewModel: HomeViewModel

    @MockK(relaxed = true)
    lateinit var apiRepository: ApiRepository

    @MockK(relaxed = true)
    lateinit var dbHelper: DatabaseHelper


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = HomeViewModel(apiRepository, dbHelper)
    }

    @Test
    fun `test search api`() {
       /* val apiResponseModel = MockDataFactory.getSearchApiResponseModel()
        every {
            apiRepository.search(any())
        } returns Single.just(apiResponseModel)

        viewModel.searchProductsResponseLiveData("123")

        Assertions.assertEquals(
            apiResponseModel,
            viewModel.getPeopleResponseMutableLiveData().value?.apiResponseData
        )*/
    }


    @Test
    fun `test convert to DB Model`() {
        val dataConverter = DataConverter()
        val actualstarwarsRoomDataModel = MockDataFactory.getstarwarsRoomDataModel()
        val result = MockDataFactory.getResultModel()
        val expectedstarwarsModel = viewModel.convertToDbModel(result, dataConverter)

        Assertions.assertEquals(expectedstarwarsModel.id, actualstarwarsRoomDataModel.id)
        Assertions.assertEquals(expectedstarwarsModel.areaName, actualstarwarsRoomDataModel.areaName)
        Assertions.assertEquals(expectedstarwarsModel.country, actualstarwarsRoomDataModel.country)
        Assertions.assertEquals(expectedstarwarsModel.region, actualstarwarsRoomDataModel.region)
        Assertions.assertEquals(expectedstarwarsModel.latitude, actualstarwarsRoomDataModel.latitude)
        Assertions.assertEquals(expectedstarwarsModel.longitude, actualstarwarsRoomDataModel.longitude)
        Assertions.assertEquals(expectedstarwarsModel.starwarsUrl, actualstarwarsRoomDataModel.starwarsUrl)
    }



}