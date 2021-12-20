package requests


import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.funinthesun.R
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class CurrentWeatherRequest(
    private val context: Context
) {
    suspend fun getData(location: LatLng) = suspendCoroutine<CurrentWeather> { cont ->
        val queue = Volley.newRequestQueue(context)

        val url = "https://api.openweathermap.org/data/2.5/weather?" +
                "lat=${location.latitude}&" +
                "lon=${location.longitude}&" +
                "appid=${context.getText(R.string.open_weather_key)}"

        val stringRequest = StringRequest(Request.Method.GET, url,

            {response ->
                val gson = Gson()
                val currentWeather = gson.fromJson(response, CurrentWeather::class.java)
                cont.resume(currentWeather)
            },

            {throw Exception("Weather API failed to respond")})

        queue.add(stringRequest)
    }



}