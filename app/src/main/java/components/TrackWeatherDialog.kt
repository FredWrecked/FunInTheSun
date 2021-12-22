package components

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import com.example.funinthesun.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.*
import model.TrackedLocation
import model.WeatherToTrack
import repository.TrackedLocationRepository
import requests.WeatherMarker.WeatherMarker
import requests.currentWeather.CurrentWeatherRequest

class TrackWeatherDialog(
    private val applicationContext: Context,
    private val map: GoogleMap,
    private val scope: CoroutineScope,
    private val trackedLocationRepository: TrackedLocationRepository
) {

    fun trackWeather(location: LatLng, ){

        val builder = AlertDialog.Builder(applicationContext)
        builder.setMessage(applicationContext.getString(R.string.track_weather_message))

        builder.setPositiveButton(applicationContext.getString(R.string.yes)) { dialog, which ->
            scope.launch {
                val currentWeather = CurrentWeatherRequest(applicationContext).getData(location)

                val weatherMarker = MarkerOptions()
                weatherMarker.position(location)

                weatherMarker.title(currentWeather.weather.first().description)

                val trackedLocation = TrackedLocation(
                    name = "Tracked Location",
                    location = location,
                    weatherToTrack = WeatherToTrack(currentWeather.weather.first().description)
                )

                trackedLocationRepository.add(trackedLocation)

                WeatherMarker().setWeatherMarker(
                    icon = currentWeather.weather.first().icon,
                    map = map,
                    marker = weatherMarker,
                    trackedLocation = trackedLocation
                )
            }

        }

        builder.setNegativeButton(applicationContext.getString(R.string.no)) { dialog, which ->
        }

        builder.show()
    }


}