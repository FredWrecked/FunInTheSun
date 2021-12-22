package requests.WeatherMarker


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import model.TrackedLocation
import java.lang.Exception


class WeatherMarker {
    fun setWeatherMarker(icon: String, marker: MarkerOptions, map: GoogleMap, trackedLocation: TrackedLocation) {
        val url = "http://openweathermap.org/img/wn/${icon}@2x.png"
        println("url:${url}")
        Picasso.get().load(url).into(PicassoMarker(map,marker,trackedLocation))
    }
}


class PicassoMarker(
    private val map: GoogleMap,
    private val marker: MarkerOptions,
    private val trackedLocation: TrackedLocation
    ) : Target {

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        println("picassoMarker onPrepareLoad : ")
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        println("picassoMarker onBitmapFailed: ${e}")
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        try {
            if (bitmap != null) {
                marker.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                val weatherMarker = map.addMarker(marker)
                weatherMarker?.tag = trackedLocation
            }
        } catch (ex: IllegalArgumentException) {
            Exception("Marker is dead")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is PicassoMarker) {
            return marker == other
        }
        return false
    }

    override fun hashCode(): Int {
        return marker.hashCode()
    }
}