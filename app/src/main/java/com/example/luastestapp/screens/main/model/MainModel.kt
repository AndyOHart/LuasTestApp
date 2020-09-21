package com.example.luastestapp.screens.main.model

import com.example.luastestapp.model.StopInfo
import io.reactivex.rxjava3.core.Observable

interface MainModel {
    fun getLuasStops(stopName: String): Observable<StopInfo>
    fun getMockLuasStop(): StopInfo
}