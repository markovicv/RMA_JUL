package com.example.rma_final_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rma_final_project.data.domain.WeatherDomain
import com.example.rma_final_project.presentation.Konstants

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var lat:Double = 0.0
    private var lon:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val weather = intent.getParcelableExtra("DETAILS")as WeatherDomain
        lat = weather.lat
        lon = weather.lon
        cityDetailsId.setText(weather.city)
        dateDetailsId.setText(weather.date)
        maxTempId.setText(Konstants.NAX_TEMP_DETAIL+weather.max_temperature)
        minTempId.setText(Konstants.MIN_TEMP_DETAIL+weather.min_temperature)
        windSpeedId.setText(Konstants.WIND_SPEED+weather.wind)
        humidityId.setText(Konstants.HUMIDITY+weather.humidity)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val city = LatLng(lat,lon)

        mMap.addMarker(MarkerOptions().position(city).title("aa"))
       //mMap.moveCamera(CameraUpdateFactory.newLatLng(city))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 12.0f))
    }
}
