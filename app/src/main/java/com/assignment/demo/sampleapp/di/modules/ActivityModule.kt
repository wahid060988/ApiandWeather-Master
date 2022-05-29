package com.assignment.demo.sampleapp.di.modules

import com.assignment.demo.sampleapp.view.MainActivity
import com.assignment.demo.sampleapp.weather.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun mainActivity(): MainActivity?
    @ContributesAndroidInjector
    fun weatherActivity(): WeatherActivity?

}