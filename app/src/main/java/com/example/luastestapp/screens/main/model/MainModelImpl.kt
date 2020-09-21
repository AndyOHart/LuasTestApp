package com.example.luastestapp.screens.main.model

import android.content.Context
import android.util.Log
import com.example.luastestapp.R
import com.example.luastestapp.model.StopInfo
import com.example.luastestapp.network.LuasApiService
import io.reactivex.rxjava3.core.Observable
import java.lang.Exception
import javax.inject.Inject

class MainModelImpl @Inject constructor(
    private val context: Context,
    private val apiService: LuasApiService
) : MainModel {

    override fun getLuasStops(stopName: String): Observable<StopInfo> =
        apiService.getLuasForecast("forecast", stopName, false)

    override fun getDueMinsString(dueMins: String): String {
        return try {
            if (dueMins.toInt() == 1)
                context.getString(R.string.minute, dueMins)
            else context.getString(R.string.minutes, dueMins)
        } catch (exception: Exception) {
            Log.d("TramListAdapter", "Error parsing dueMins, actual value is: $dueMins")
            dueMins
        }
    }

}