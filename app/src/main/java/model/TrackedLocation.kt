package model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class TrackedLocation(
    val guid: String = UUID.randomUUID().toString(),
    val name: String,
    val location: LatLng,
    val weatherToTrack: WeatherToTrack
    ) {
}