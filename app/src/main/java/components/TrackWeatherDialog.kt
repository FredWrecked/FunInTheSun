package components

import android.app.AlertDialog
import android.content.Context
import com.example.funinthesun.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*
import requests.currentWeather.CurrentWeatherRequest

class TrackWeatherDialog(
    private val applicationContext: Context,
    private val map: GoogleMap,
    private val location: LatLng,
    private val scope: CoroutineScope
) {

    fun show(
    ){
        val builder = AlertDialog.Builder(applicationContext)
        builder.setMessage(applicationContext.getString(R.string.track_weather_message))

        builder.setPositiveButton(applicationContext.getString(R.string.yes)) { dialog, which ->
            scope.launch {
                val currentWeather = CurrentWeatherRequest(applicationContext).getData(location)
                val newMarker = MarkerOptions()
                newMarker.position(location)
                newMarker.title(currentWeather.weather.first().description)
                map.clear()
                map.addMarker(newMarker)
            }

        }

        builder.setNegativeButton(applicationContext.getString(R.string.no)) { dialog, which ->
        }

        builder.show()
    }


}