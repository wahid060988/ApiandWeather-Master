package com.assignment.demo.starwarsapp.converter

import com.assignment.demo.starwarsapp.factory.MockDataFactory
import com.assignment.demo.starwarsapp.utils.DataConverter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataConverterTest {

    @MockK(relaxUnitFun = true)
    lateinit var dataConverter: DataConverter

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataConverter = DataConverter()
    }

    @Test
    fun `test convert to DB Model`() {

        val actualstarwarsRoomDataModel = MockDataFactory.getstarwarsRoomDataModel()
        val result = MockDataFactory.getResultModel()
        val expectedstarwarsModel = dataConverter.convertToDbModel(result)

        Assertions.assertEquals(expectedstarwarsModel.id, actualstarwarsRoomDataModel.id)
        Assertions.assertEquals(expectedstarwarsModel.areaName, actualstarwarsRoomDataModel.areaName)
        Assertions.assertEquals(expectedstarwarsModel.country, actualstarwarsRoomDataModel.country)
        Assertions.assertEquals(expectedstarwarsModel.region, actualstarwarsRoomDataModel.region)
        Assertions.assertEquals(expectedstarwarsModel.latitude, actualstarwarsRoomDataModel.latitude)
        Assertions.assertEquals(
            expectedstarwarsModel.longitude,
            actualstarwarsRoomDataModel.longitude
        )
        Assertions.assertEquals(
            expectedstarwarsModel.starwarsUrl,
            actualstarwarsRoomDataModel.starwarsUrl
        )
    }

    @Test
    fun `test prepare database id from result`() {
        val expectedID = "area, region"
        val result = MockDataFactory.getResultModel()
        val actualId = dataConverter.prepareDatabaseIdFromResult(result)

        Assertions.assertEquals(expectedID, actualId)
    }

    @Test
    fun `test prepare display id from result`() {
        val expectedID = "area, region, country"
        val result = MockDataFactory.getResultModel()
        val actualId = dataConverter.prepareDisplayIdFromResult(result)

        Assertions.assertEquals(expectedID, actualId)
    }
}