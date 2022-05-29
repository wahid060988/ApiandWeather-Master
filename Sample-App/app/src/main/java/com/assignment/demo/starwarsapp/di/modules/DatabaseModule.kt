package com.assignment.demo.starwarsapp.di.modules

import android.content.Context
import com.assignment.demo.starwarsapp.db.room.DatabaseBuilder
import com.assignment.demo.starwarsapp.db.room.DatabaseHelperImpl
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDBHelper(context : Context): DatabaseHelperImpl {
        return DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
    }
}