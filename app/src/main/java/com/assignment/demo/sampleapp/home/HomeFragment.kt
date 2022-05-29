package com.assignment.demo.sampleapp.home

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import com.assignment.demo.sampleapp.R
import com.assignment.demo.sampleapp.api_service.SampleAppApiRequest
import com.assignment.demo.sampleapp.base.BaseApiResponseModel
import com.assignment.demo.sampleapp.base.BaseFragment
import com.assignment.demo.sampleapp.databinding.FragmentHomeBinding
import com.assignment.demo.sampleapp.datamodel.peoples.RandomResults
import com.assignment.demo.sampleapp.datamodel.peoples.RandomUserResponseModel
import com.assignment.demo.sampleapp.datamodel.peoples.Results
import com.assignment.demo.sampleapp.db.room.DatabaseBuilder
import com.assignment.demo.sampleapp.db.room.DatabaseHelperImpl
import com.assignment.demo.sampleapp.details.DetailsFragment
import com.assignment.demo.sampleapp.home.adapter.AutoSuggestAdapter
import com.assignment.demo.sampleapp.home.adapter.PeopleListRecyclerViewAdapter
import com.assignment.demo.sampleapp.home.viewmodel.HomeViewModel
import com.assignment.demo.sampleapp.home.viewmodel.HomeViewModelFactory
import com.assignment.demo.sampleapp.repository.ApiRepository
import com.assignment.demo.sampleapp.retrofit.ApiRetrofit
import com.assignment.demo.sampleapp.utils.DataConverter
import com.assignment.demo.sampleapp.utils.NotificationHelper
import com.assignment.demo.sampleapp.view.MainActivity
import javax.inject.Inject

class HomeFragment : BaseFragment() {

  private var nextPageUrl: String? = ""
  private var previousPageUrl: String? = ""

  @Inject
  lateinit var ApiRetrofit: ApiRetrofit

  @Inject
  lateinit var dataConverter: DataConverter

  @Inject
  lateinit var notificationHelper: NotificationHelper

  private lateinit var autoSuggestAdapter: AutoSuggestAdapter
  private lateinit var homeViewModel: HomeViewModel
  private lateinit var binding: FragmentHomeBinding
  private val TRIGGER_AUTO_COMPLETE = 100
  private val AUTO_COMPLETE_DELAY: Long = 300
  private var handler: Handler? = null
  private lateinit var peopleListAdapter: PeopleListRecyclerViewAdapter

  private lateinit var randonUserList: ArrayList<RandomResults>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val apiRequest = ApiRetrofit.getRetrofitInstance()
      .create(SampleAppApiRequest::class.java)
    val apiRepository = ApiRepository(apiRequest)

    val dbHelper = DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
    val dataConverter = DataConverter()
    val factory = HomeViewModelFactory(apiRepository, dbHelper, dataConverter)
    homeViewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentHomeBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initObservers()
    setupSearchAutoCompleteView(binding.includeAutocompleteSearchLayout.autoCompleteSearch)
    randonUserList = ArrayList()

  }

  override fun onResume() {
    super.onResume()
    binding.includeAutocompleteSearchLayout.autoCompleteSearch.text.clear()
  }


  private fun setupSearchAutoCompleteView(autoCompleteTextView: AutoCompleteTextView) {
    autoSuggestAdapter = AutoSuggestAdapter(
      requireContext(),
      android.R.layout.simple_list_item_1
    )
    autoCompleteTextView.dropDownWidth = resources.displayMetrics.widthPixels - 250
    autoCompleteTextView.threshold = 3
    autoCompleteTextView.setAdapter(autoSuggestAdapter)
    autoCompleteTextView.onItemClickListener =
      AdapterView.OnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
        onAutoCompleteRowClick(autoSuggestAdapter.getObject(position))
      }

    autoCompleteTextView.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        handler?.removeMessages(TRIGGER_AUTO_COMPLETE)
        handler?.sendEmptyMessageDelayed(
          TRIGGER_AUTO_COMPLETE,
          AUTO_COMPLETE_DELAY
        )
      }

      override fun afterTextChanged(s: Editable) {}
    })
    handler = Handler { msg: Message ->
      if (msg.what == TRIGGER_AUTO_COMPLETE) {
        val selectedText = autoCompleteTextView.text
        if (!TextUtils.isEmpty(selectedText)) {
          binding.includeAutocompleteSearchLayout.progressLoading.visibility =
            View.VISIBLE
          fetchSearchDataFromApi(selectedText.toString())
        }
      }
      false
    }
  }


  private fun onAutoCompleteRowClick(result: Results) {
    binding.includeAutocompleteSearchLayout.autoCompleteSearch.text.clear()
    (activity as MainActivity).hideKeyboard()
  }


  private fun initObservers() {
    showProgressDialog()
    homeViewModel.getRandomUserList()
    homeViewModel.getRemoteResponseRandomUserMutableLiveData()
      .observe(viewLifecycleOwner) { baseApiResponseModel: BaseApiResponseModel<RandomUserResponseModel>? ->
        hideProgressDialog()
        if (baseApiResponseModel != null && baseApiResponseModel.isSuccessful) {
          val apiResponseData =
            baseApiResponseModel.apiResponseData as RandomUserResponseModel

          homeViewModel.saveData(apiResponseData)

          apiResponseData?.let {
            if (randonUserList.isNotEmpty()) randonUserList.clear()
            randonUserList.addAll(apiResponseData.results)
            showRandomUserList(randonUserList)
          }

          homeViewModel.getDBData()
        } else {
          val errorMsgString = resources.getString(R.string.error_msg)
          notificationHelper.setSnackBar(binding.root, errorMsgString)
        }
      }

    binding.llyoutHome.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
      // on scroll change we are checking when users scroll as bottom.
      if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
        showProgressDialog()
        homeViewModel.getRandomUserList()
        homeViewModel.getRemoteResponseRandomUserMutableLiveData()
          .observe(viewLifecycleOwner) { baseApiResponseModel: BaseApiResponseModel<RandomUserResponseModel>? ->
            hideProgressDialog()
            if (baseApiResponseModel != null && baseApiResponseModel.isSuccessful) {
              val apiResponseData =
                baseApiResponseModel.apiResponseData as RandomUserResponseModel

              homeViewModel.saveData(apiResponseData)

              apiResponseData?.let {
                if (randonUserList.isNotEmpty()) randonUserList.clear()
                randonUserList.addAll(apiResponseData.results)
                showRandomUserList(randonUserList)
              }

              homeViewModel.getDBData()
            } else {
              val errorMsgString = resources.getString(R.string.error_msg)
              notificationHelper.setSnackBar(binding.root, errorMsgString)
            }          }
      }
    })


    homeViewModel.getPeopleResponseMutableLiveData().observe(viewLifecycleOwner) {
      // Populate UI for search result
      binding.includeAutocompleteSearchLayout.progressLoading.visibility =
        View.INVISIBLE
      Toast.makeText(activity,"Searched Name "+it.get(0).name, Toast.LENGTH_SHORT).show()
    }
  }

  private fun fetchSearchDataFromApi(searchQuery: String) {
    homeViewModel.searchPeoplesResponseLiveData(searchQuery)
  }

  private fun openDetailsFragment(selectedId: RandomResults) {
    val detailsFragment = DetailsFragment()
    val bundle = Bundle()
    bundle.putString("name", selectedId.name.first)
    bundle.putString("gender", selectedId.gender)
    bundle.putString("email", selectedId.email)
    bundle.putString("phone", selectedId.phone)
    bundle.putString("cell", selectedId.cell)
    bundle.putString("nat", selectedId.nat)
    detailsFragment.arguments = bundle
    (activity as MainActivity).addFragment(detailsFragment)
  }

  fun onRowItemClicked(selectedId: RandomResults) {
    openDetailsFragment(selectedId)
  }

  private fun showRandomUserList(remote: List<RandomResults>) {
    peopleListAdapter = PeopleListRecyclerViewAdapter(this, remote)
    binding.recyclerView.visibility = View.VISIBLE
    binding.tvNoData.visibility = View.GONE
    binding.recyclerView.adapter = peopleListAdapter
    peopleListAdapter.notifyDataSetChanged()

  }

  private fun showProgressDialog() {
    binding.progressBar.visibility = View.VISIBLE
  }

  private fun hideProgressDialog() {
    binding.progressBar.visibility = View.GONE
  }
}