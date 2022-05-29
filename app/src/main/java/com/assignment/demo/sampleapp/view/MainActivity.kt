package com.assignment.demo.sampleapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.demo.sampleapp.R
import com.assignment.demo.sampleapp.base.BaseActivity
import com.assignment.demo.sampleapp.databinding.ActivityMainBinding
import com.assignment.demo.sampleapp.home.HomeFragment
import com.assignment.demo.sampleapp.weather.WeatherActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        val view: View = binding.root
        binding.btnPhase1.setOnClickListener {
            openHomeFragment()
            binding.btnPhase1.visibility=View.GONE
            binding.btnPhase2.visibility=View.GONE
        }

        binding.btnPhase2.setOnClickListener {
            openWeather()
            binding.btnPhase1.visibility=View.GONE
            binding.btnPhase2.visibility=View.GONE
        }


        setContentView(view)

    }

    private fun openHomeFragment() {
        val homeFragment = HomeFragment()
        replaceFragment(homeFragment)
    }

    private fun openWeather() {
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
        finish()
    }
     public override fun addFragment(fragment: Fragment) {
        super.addFragment(fragment)
    }

    fun setStatusBarGreen() {
        super.setStatusBar(resources.getColor(R.color.fragment_background))
    }
}