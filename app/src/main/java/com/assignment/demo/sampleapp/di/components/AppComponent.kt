package com.assignment.demo.sampleapp.di.components

import com.assignment.demo.sampleapp.di.AppModule
import com.assignment.demo.sampleapp.di.SampleAppApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<SampleAppApplication?> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<SampleAppApplication?>
}