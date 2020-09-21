package com.example.luastestapp.di

import com.example.luastestapp.network.LuasApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://luasforecasts.rpa.ie/xml/")
        .addConverterFactory(TikXmlConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    //  https://luasforecasts.rpa.ie/xml/get.ashx?action=forecast&stop=stir&encrypt=false
    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient()


    @Singleton
    @Provides
    fun provideLuasService(retrofit: Retrofit): LuasApiService =
        retrofit.create(LuasApiService::class.java)

}