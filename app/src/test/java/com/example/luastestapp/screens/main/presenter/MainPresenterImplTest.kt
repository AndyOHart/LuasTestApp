package com.example.luastestapp.screens.main.presenter

import android.util.Log
import com.example.luastestapp.screens.main.model.MainModel
import com.example.luastestapp.screens.main.view.MainView
import com.example.luastestapp.utils.DateUtils
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import testUtils.TestUtils
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(PowerMockRunner::class)
@PrepareForTest(DateUtils::class)
class MainPresenterImplTest {

    private lateinit var view: MainView
    private lateinit var model: MainModel
    private lateinit var presenter: MainPresenter
    private lateinit var dateUtilsMock: MockedStatic<DateUtils>

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupRxSchedulers() {
            val immediate: Scheduler = object : Scheduler() {
                override fun schedulePeriodicallyDirect(
                    run: Runnable?,
                    initialDelay: Long,
                    period: Long,
                    unit: TimeUnit?
                ): Disposable {
                    return super.schedulePeriodicallyDirect(run, 0, period, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorWorker(
                        Executor { obj: Runnable -> obj.run() },
                        true,
                        true
                    )
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        }
    }


    @Before
    fun setup() {
        view = Mockito.mock(MainView::class.java)
        model = Mockito.mock(MainModel::class.java)
        presenter = MainPresenterImpl(view, model)

        Mockito.`when`(view.observeRefreshButtonClick()).thenReturn(Observable.empty())
    }

    @Test
    fun testUpdateLuasStopWithAMTrams() {
        dateUtilsMock = Mockito.mockStatic(DateUtils::class.java)
        val luasStops = TestUtils.getMockLuasStop()

        Mockito.`when`(model.getLuasStops(anyString())).thenReturn(Observable.just(luasStops))
        Mockito.`when`(DateUtils.isAM()).thenReturn(true)

        presenter.onCreate()

        Mockito.verify(model, times(1)).getLuasStops(anyString())
        Mockito.verify(view, times(1)).showLoading()
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, times(1)).setTitle(anyString(), anyString(), anyString())
        Mockito.verify(view, times(1)).showNoTramsAvailable(true)
        Mockito.verify(view, times(1)).updateRecyclerView(luasStops.directions[1].tram)

        dateUtilsMock.close()
    }

    @Test
    fun testUpdateLuasStopWithPMTrams() {
        dateUtilsMock = Mockito.mockStatic(DateUtils::class.java)
        val luasStops = TestUtils.getMockLuasStop()

        Mockito.`when`(model.getLuasStops(anyString())).thenReturn(Observable.just(luasStops))
        Mockito.`when`(DateUtils.isAM()).thenReturn(false)

        presenter.onCreate()

        Mockito.verify(model, times(1)).getLuasStops(anyString())
        Mockito.verify(view, times(1)).showLoading()
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, times(1)).setTitle(anyString(), anyString(), anyString())
        Mockito.verify(view, times(1)).showNoTramsAvailable(true)
        Mockito.verify(view, times(1)).updateRecyclerView(luasStops.directions[0].tram)

        dateUtilsMock.close()
    }

    @Test
    fun testGetLuasStopCallFails() {
        Mockito.`when`(model.getLuasStops(anyString())).thenReturn(Observable.error(Throwable()))

        presenter.onCreate()

        Mockito.verify(model, times(1)).getLuasStops(anyString())
        Mockito.verify(view, times(1)).showLoading()
        Mockito.verify(view, times(1)).hideLoading()
        Mockito.verify(view, times(1)).showSnackBar(anyInt())
        Mockito.verify(view, times(1)).showNoTramsAvailable(false)
    }

}