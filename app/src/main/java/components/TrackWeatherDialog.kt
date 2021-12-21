package components

import android.app.AlertDialog
import android.content.Context
import com.example.funinthesun.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*
import model.TrackedLocation
import model.WeatherToTrack
import repository.TrackedLocationRepository
import requests.currentWeather.CurrentWeatherRequest

class TrackWeatherDialog(
    private val applicationContext: Context,
    private val map: GoogleMap,
    private val location: LatLng,
    private val scope: CoroutineScope,
    private val trackedLocationRepository: TrackedLocationRepository
) {

    fun trackWeather(
    ){
        val builder = AlertDialog.Builder(applicationContext)
        builder.setMessage(applicationContext.getString(R.string.track_weather_message))

        builder.setPositiveButton(applicationContext.getString(R.string.yes)) { dialog, which ->
            scope.launch {
                val currentWeather = CurrentWeatherRequest(applicationContext).getData(location)
                val newMarker = MarkerOptions()
                newMarker.position(location)
                newMarker.title(currentWeather.weather.first().description)
                map.addMarker(newMarker)
                val trackedLocation = TrackedLocation(
                    name = "Tracked Location",
                    marker = newMarker,
                    weatherToTrack = WeatherToTrack(currentWeather.weather.first().description)
                )
                trackedLocationRepository.add(trackedLocation)
            }

        }

        builder.setNegativeButton(applicationContext.getString(R.string.no)) { dialog, which ->
        }

        builder.show()
    }


}