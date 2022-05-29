package com.assignment.demo.starwarsapp.db.room

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.demo.starwarsapp.factory.MockDataFactory
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {

    private lateinit var starwarsDao: starwarsDao
    private lateinit var appDatabase: AppDatabase

    @Before
    public override fun setUp() {
        val context = mock(Context::class.java)
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        starwarsDao = appDatabase.starwarsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun `test DB insertSearchData and fetchSearchData queries`(): Unit = runBlocking {
        val starwarsRoomDataModel = MockDataFactory.getstarwarsRoomDataModel()
        starwarsDao.insert(starwarsRoomDataModel)
        val starwarsRoomDataModels = starwarsDao.fetchSearchData()
        Assertions.assertThat(starwarsRoomDataModels.contains(starwarsRoomDataModel)).isTrue
    }

    @Test
    fun `test DB duplicate insertSearchData query`(): Unit = runBlocking {
        val starwarsRoomDataModel = MockDataFactory.getstarwarsRoomDataModel()
        starwarsDao.insert(starwarsRoomDataModel)
        starwarsDao.insert(starwarsRoomDataModel)
        val starwarsRoomDataModels = starwarsDao.fetchSearchData()
        org.junit.jupiter.api.Assertions.assertEquals(starwarsRoomDataModels.size, 1)
    }

    @Test
    fun `test DB fetchSearchData query should give only 10 results`(): Unit = runBlocking {
        for (i in 1 until 15) {
            val starwarsRoomDataModel = MockDataFactory.getstarwarsRoomDataModel("id-$i")
            starwarsDao.insert(starwarsRoomDataModel)
        }
        val starwarsRoomDataModels = starwarsDao.fetchSearchData()
        org.junit.jupiter.api.Assertions.assertEquals(starwarsRoomDataModels.size, 10)
    }

}