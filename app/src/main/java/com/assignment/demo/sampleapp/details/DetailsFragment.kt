package com.assignment.demo.sampleapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.assignment.demo.sampleapp.R
import com.assignment.demo.sampleapp.api_service.SampleAppApiRequest
import com.assignment.demo.sampleapp.base.BaseFragment
import com.assignment.demo.sampleapp.constants.AppConstants
import com.assignment.demo.sampleapp.databinding.FragmentDetailsBinding
import com.assignment.demo.sampleapp.datamodel.peoples.Results
import com.assignment.demo.sampleapp.details.viewmodel.DetailViewModelFactory
import com.assignment.demo.sampleapp.details.viewmodel.DetailsViewModel
import com.assignment.demo.sampleapp.repository.ApiRepository
import com.assignment.demo.sampleapp.retrofit.ApiRetrofit
import com.assignment.demo.sampleapp.utils.NotificationHelper
import java.util.*
import javax.inject.Inject

class DetailsFragment : BaseFragment() {

    @Inject
    lateinit var ApiRetrofit: ApiRetrofit

    @Inject
    lateinit var notificationHelper: NotificationHelper

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private var name: String = ""
    private var gender: String = ""
    private var email: String = ""
    private var phone: String = ""
    private var cell: String = ""
    private var nat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiRequest = ApiRetrofit.getRetrofitInstance()
            .create(SampleAppApiRequest::class.java)
        val apiRepository = ApiRepository(apiRequest)
        val factory = DetailViewModelFactory(apiRepository)
        detailsViewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        name= arguments?.getString("name").toString()
        gender= arguments?.getString("gender").toString()
        email= arguments?.getString("email").toString()
        phone= arguments?.getString("phone").toString()
        cell= arguments?.getString("cell").toString()
        nat= arguments?.getString("nat").toString()
        binding.tvStarName.text = name
        binding.tvStarGender.text = gender
        binding.tvStarEmail.text = email
        binding.tvStarPhone.text = phone
        binding.tvStarCell.text = cell
        binding.tvStarNat.text = nat
        hideProgressDialog()
    }
    private fun showProgressDialog() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun hideProgressDialog() {
        binding.progressBar.visibility = View.GONE
    }

}