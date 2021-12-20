package components

import android.app.AlertDialog
import android.content.Context
import com.example.funinthesun.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import requests.currentWeather.CurrentWeatherRequest
import kotlin.coroutines.CoroutineContext

class TrackWeatherDialog(
    private val applicationContext: Context,
    private val map: GoogleMap,
    private val location: LatLng,
    private val coroutineContext: CoroutineContext,
    private val markerTitle: String
) {

    fun show(
    ){
        val builder = AlertDialog.Builder(applicationContext)
        builder.setMessage(applicationContext.getString(R.string.track_weather_message))

        builder.setPositiveButton(applicationContext.getString(R.string.yes)) { dialog, which ->


            val scope = CoroutineScope(coroutineContext)
            val currentWeatherFinder = CurrentWeatherRequest(applicationContext)

            val newMarker = MarkerOptions()
            newMarker.position(location)
            newMarker.title(markerTitle)

            map.clear()
            map.addMarker(newMarker)

//            scope.launch {
//            }
//            scope.cancel()

        }

        builder.setNegativeButton(applicationContext.getString(R.string.no)) { dialog, which ->
            println("No")
        }

        builder.show()
    }


}