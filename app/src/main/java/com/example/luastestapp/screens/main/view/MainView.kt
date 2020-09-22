package com.example.luastestapp.screens.main.view

import androidx.annotation.StringRes
import com.example.luastestapp.model.Tram
import io.reactivex.rxjava3.core.Observable

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun observeRefreshButtonClick(): Observable<Unit>
    fun showSnackBar(@StringRes textRes: Int)
    fun updateRecyclerView(tramList: List<Tram>)
    fun showNoTramsAvailable(areTramsAvailable: Boolean)
    fun setTitle(stopName: String, direction: String, message: String)
}