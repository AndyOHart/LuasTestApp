package com.example.luastestapp.screens.main.di

import android.content.Context
import com.example.luastestapp.network.LuasApiService
import com.example.luastestapp.screens.main.model.MainModelImpl
import com.example.luastestapp.screens.main.presenter.MainPresenterImpl
import com.example.luastestapp.screens.main.view.MainViewImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun providePresenter(view: MainViewImpl, interactor: MainModelImpl) =
        MainPresenterImpl(view, interactor)

    @Provides
    fun provideInteractor(apiService: LuasApiService) = MainModelImpl(apiService)

    @Provides
    @ActivityScoped
    fun provideView(@ActivityContext context: Context) = MainViewImpl(context)
}