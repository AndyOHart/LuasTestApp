package com.example.luastestapp.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.luastestapp.screens.main.presenter.MainPresenterImpl
import com.example.luastestapp.screens.main.view.MainViewImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: MainPresenterImpl

    @Inject
    lateinit var view: MainViewImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view)

        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}