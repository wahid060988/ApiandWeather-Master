package com.assignment.demo.sampleapp.base

import android.os.Bundle
import android.view.View
import com.assignment.demo.sampleapp.view.MainActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).setStatusBarGreen()

        try {
            (requireActivity() as MainActivity).supportActionBar!!.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}