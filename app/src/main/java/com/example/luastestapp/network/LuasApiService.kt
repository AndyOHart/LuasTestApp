package com.example.luastestapp.network

import com.example.luastestapp.model.StopInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LuasApiService {

    @GET("get.ashx")
    fun getLuasForecast(
        @Query("action") action: String,
        @Query("stop") stopName: String,
        @Query("encrypt") encrypt: Boolean
    ): Observable<StopInfo>
}