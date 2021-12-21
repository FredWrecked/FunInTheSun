package repository

import android.content.Context
import com.example.funinthesun.R
import model.TrackedLocation
import java.lang.Exception

class TrackedLocationRepository(private val context: Context) {

    private val trackedLocations = mutableListOf<TrackedLocation>()

    fun add(trackedLocation: TrackedLocation) {
        trackedLocations.add(trackedLocation)
    }

    fun remove(trackedLocation: TrackedLocation) {
        trackedLocations.remove(trackedLocation)
    }

    fun readById(id: String): TrackedLocation {
        val result = trackedLocations.filter { it.guid == id }
        if (result.size > 1){
            throw  Exception(context.getString(R.string.no_results_found))
        }
        else if (result.isEmpty()){
            throw  Exception(context.getString(R.string.multiple_results_found_one_expected))
        }
        return result.first()
    }

    fun readAll(): List<TrackedLocation> {
        return trackedLocations
    }


}
