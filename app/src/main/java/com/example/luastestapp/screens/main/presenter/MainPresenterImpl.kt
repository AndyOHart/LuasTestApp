package com.example.luastestapp.screens.main.presenter

import com.example.luastestapp.R
import com.example.luastestapp.model.Direction
import com.example.luastestapp.model.StopInfo
import com.example.luastestapp.model.StopNames
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
    private val model: MainModel
) : MainPresenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val defaultStop = if (DateUtils.isAM()) StopNames.marlborough else StopNames.stillorgan

    override fun onCreate() {
        compositeDisposable.add(handleRefreshButton())
        compositeDisposable.add(getLuasStops())
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    private fun getLuasStops(): Disposable {
        return model.getLuasStops(defaultStop)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.hideLoading() }
            .subscribe(
                { luasStop -> handleLuasStopResponse(luasStop) },
                {
                    view.showSnackBar(R.string.error_network)
                    view.showNoTramsAvailable(false)
                })
    }

    private fun handleLuasStopResponse(luasStop: StopInfo) {
        val isAM = DateUtils.isAM()
        val currentDirection = if (isAM) luasStop.directions[1] else luasStop.directions[0]
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