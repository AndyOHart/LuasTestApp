package com.example.luastestapp.screens.main.presenter

import com.example.luastestapp.model.Direction
import com.example.luastestapp.model.StopInfo
import com.example.luastestapp.screens.main.model.MainModel
import com.example.luastestapp.screens.main.view.MainView
import com.example.luastestapp.utils.DateUtils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
    private val view: MainView,
    private val interactor: MainModel
) : MainPresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val defaultStop = if (DateUtils.currentlyBetweenMiddayAndMidnight()) "sti" else "mar"

    override fun onCreate() {
        compositeDisposable.add(handleRefreshButton())
        compositeDisposable.add(getLuasStops())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun getLuasStops(): Disposable {
        return interactor.getLuasStops(defaultStop)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { luasStop -> handleLuasStopResponse(luasStop) },
                { view.showSnackBar("Network Error") })
    }


    private fun handleLuasStopResponse(luasStop: StopInfo) {
        val currentlyBetweenMiddayAndMidnight = DateUtils.currentlyBetweenMiddayAndMidnight()
        val currentDirection =
            if (currentlyBetweenMiddayAndMidnight) luasStop.directions[0] else luasStop.directions[1]
        val tramsAvailable = areTramsAvailable(currentDirection)

        view.setTitle(luasStop.stop, currentDirection.name, luasStop.message)

        if (!tramsAvailable) {
            view.showNoTramsAvailable(false)
        } else {
            view.showNoTramsAvailable(true)
            view.updateRecyclerView(currentDirection.tram)
        }
    }

    private fun areTramsAvailable(direction: Direction): Boolean {
        return direction.tram.isNotEmpty() && direction.tram[0].dueMins.isNotEmpty()
    }

    private fun handleRefreshButton(): Disposable {
        return view.observeRefreshButtonClick()
            .subscribe { compositeDisposable.add(getLuasStops()) }
    }

}