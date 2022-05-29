package com.assignment.demo.sampleapp.di.modules

import com.assignment.demo.sampleapp.retrofit.ApiRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UtilsModule {

    @Provides
    fun provideRetrofit(ApiRetrofit: ApiRetrofit): Retrofit {
        return ApiRetrofit.getRetrofitInstance()
    }
}