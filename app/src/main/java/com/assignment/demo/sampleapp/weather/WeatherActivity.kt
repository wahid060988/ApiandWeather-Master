package com.assignment.demo.sampleapp.weather

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.assignment.demo.sampleapp.base.BaseActivity
import com.assignment.demo.sampleapp.databinding.ActivityMainBinding
import com.assignment.demo.sampleapp.databinding.ActivityWeatherBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.R
import org.json.JSONObject

class WeatherActivity : BaseActivity() {
    //weather url to get JSON
    var weather_url1 = ""
    //api id for url
    var api_id1 = "2c1c8d05e2db49f998879325a251cda1"
    private lateinit var binding: ActivityWeatherBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(getLayoutInflater());
        val view: View = binding.root
        //link the textView in which the temperature will be displayed
        //create an instance of the Fused Location Provider Client
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        Log.e("lat", weather_url1)
        //on clicking this button function to get the coordinates will be called
        binding.btVar1.setOnClickListener {
            Log.e("lat", "onClick")
            checkLocationEnabled()
        }
        setContentView(view)

    }

    override fun onResume() {
        super.onResume()
        obtainLocation()
    }

    @SuppressLint("MissingPermission")
    private fun obtainLocation(){
        Log.e("lat", "function")
        //get the last location
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if(location?.latitude==null&&location?.longitude==null)
                {
                    var lat:Double=13.028882
                    var lon:Double=80.2343758
                    weather_url1 = "https://api.weatherbit.io/v2.0/current?" + "lat=" + lat +"&lon="+ lon + "&key="+ api_id1
                    Log.e("fusedlocation", "null")
                }
                else
                {
                    Log.e("fusedlocation", "not null")
                    weather_url1 = "https://api.weatherbit.io/v2.0/current?" + "lat=" + location?.latitude +"&lon="+ location?.longitude + "&key="+ api_id1
                }
                getTemp()
            }
    }

    fun getTemp() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url: String = weather_url1
        Log.e("lat", url)
        // Request a string response from the provided URL.
        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                Log.e("lat", response.toString())
                //get the JSON object
                val obj = JSONObject(response)
                //get the Array from obj of name - "data"
                val arr = obj.getJSONArray("data")
                Log.e("lat obj1", arr.toString())
                //get the JSON object from the array at index position 0
                val obj2 = arr.getJSONObject(0)
                Log.e("lat obj2", obj2.toString())
                //set the temperature and the city name using getString() function
                binding.textView.text = obj2.getString("temp")+" deg Celcius in "+obj2.getString("city_name")
            },
            //In case of any error
            Response.ErrorListener { binding.textView!!.text = "Something Went Wrong!!" })
        queue.add(stringReq)
    }

    fun checkLocationEnabled() {
        val manager = getSystemService(LOCATION_SERVICE) as LocationManager
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps()
        }
        else
        {
            requestlocation()
        }
    }

    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Enable Location")
            .setCancelable(false)
            .setPositiveButton(
                "Yes"
            ) { dialog, id -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) }
            .setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    private  fun requestlocation()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !==
            PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        obtainLocation()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}