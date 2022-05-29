package com.assignment.demo.sampleapp.di.modules

import com.assignment.demo.sampleapp.details.DetailsFragment
import com.assignment.demo.sampleapp.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun homeFragment(): HomeFragment?

    @ContributesAndroidInjector
    fun detailsFragment(): DetailsFragment?
}