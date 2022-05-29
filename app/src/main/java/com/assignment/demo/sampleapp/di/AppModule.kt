package com.assignment.demo.sampleapp.di

import android.app.Application
import android.content.Context
import com.assignment.demo.sampleapp.di.modules.ActivityModule
import com.assignment.demo.sampleapp.di.modules.FragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class, ActivityModule::class, FragmentModule::class])
abstract class AppModule {
    @Binds
    abstract fun application(app: SampleAppApplication?): Application?
    @Binds
    abstract fun applicationContext(app: Application?): Context?
}