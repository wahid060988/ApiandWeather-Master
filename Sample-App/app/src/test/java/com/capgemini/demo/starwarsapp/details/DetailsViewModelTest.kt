package com.assignment.demo.starwarsapp.details

import com.assignment.demo.starwarsapp.details.viewmodel.DetailsViewModel
import com.assignment.demo.starwarsapp.factory.DetailsMockDataFactory
import com.assignment.demo.starwarsapp.factory.MockDataFactory
import com.assignment.demo.starwarsapp.home.ApiRepository
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
class DetailsViewModelTest {

    lateinit var viewModel: DetailsViewModel

    @MockK(relaxed = true)
    lateinit var apiRepository: ApiRepository


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = DetailsViewModel(apiRepository)
    }

    @Test
    fun `test starwars details api`() {
        val starwarsApiResponseModel = DetailsMockDataFactory.getstarwarsApiResponseModel()
        every {
            apiRepository.details(any())
        } returns Single.just(starwarsApiResponseModel)

        viewModel.fetchPeopleResultsResponseLiveData("123")

        Assertions.assertEquals(
            starwarsApiResponseModel,
            viewModel.getstarwarsMutableLiveData().value?.apiResponseData
        )
    }

    @Test
    fun `test geo location text`() {
        val expectedValue = "11.11,22.22"
        val roomDataModel = MockDataFactory.getstarwarsRoomDataModel()
        val actual = viewModel.getGeoLocationText(roomDataModel)
        Assertions.assertEquals(expectedValue, actual)
    }

    @Test
    fun `test temperature text`() {
        val expectedValue = "37 C"
        val currentCondition = DetailsMockDataFactory.getCurrentCondition()
        val actual = viewModel.getTemperatureText(currentCondition)
        Assertions.assertEquals(expectedValue, actual)
    }


}