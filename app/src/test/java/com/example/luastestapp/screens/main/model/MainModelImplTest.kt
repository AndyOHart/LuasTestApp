package com.example.luastestapp.screens.main.model

import android.content.Context
import android.os.Build
import android.util.Log
import com.example.luastestapp.network.LuasApiService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.core.classloader.annotations.PrepareForTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
@PrepareForTest(Log::class)
class MainModelImplTest {

    private lateinit var luasApiService: LuasApiService
    private lateinit var context: Context
    private lateinit var model: MainModel
    private lateinit var logMock: MockedStatic<Log>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this);
        context = RuntimeEnvironment.application
        luasApiService = Mockito.mock(LuasApiService::class.java)
        model = MainModelImpl(context, luasApiService)
    }

    @Test
    fun testGetDueMinsStringReturnsDue() {
        model.getDueMinsString("DUE")
        Assert.assertEquals("DUE", model.getDueMinsString("DUE"))
    }

    @Test
    fun testGetDueMinsStringReturnsMinute() {
        logMock = Mockito.mockStatic(Log::class.java)
        val actual = model.getDueMinsString("1")
        Assert.assertEquals("1 Minute", actual)
        logMock.close()
    }

    @Test
    fun testGetDueMinsStringReturnsMinutes() {
        logMock = Mockito.mockStatic(Log::class.java)
        val actual = model.getDueMinsString("20")
        Assert.assertEquals("20 Minutes", actual)
        logMock.close()
    }
}