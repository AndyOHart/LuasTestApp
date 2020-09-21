# Luas Test App
[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.3.72-blue.svg)](https://kotlinlang.org)
[![AGP](https://img.shields.io/badge/AGP-3.6.3-blue?style=flat)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-5.6.4-blue?style=flat)](https://gradle.org)

This is a basic Luas application done as a tech test.
It includes quite a modern architecture and uses new libraries where possible

## Overview
 * 100% Kotlin
 * RxMVP Architecture (https://github.com/patrick-doyle/android-rxmvp)
 * Uses material components
 * Uses classic libraries such as Retrofit, RxJava, JodaTime
 * Library called TikXML was used to parse the XML server response
 * Hilt for DI
 * Some small tests

## Choices
I chose to use RxMVP as I worked with it a lot in my previous job and found it very easy to understand
Hilt was used as I am currently trying to learn a bit more about it.
TikXML was a library I found to allow easy parsing of XML data and add a converter to Retrofit
JodaTime made the time handling aspect quite easy.

RxJava is used for observing on view interactions, these callbacks are then handled in the Presenter class.
You can see from commits that I had a getMockLuasStops method which returned a mock object, this was done as
during the night no Luas stops were available, so it was to test when I worked on it at night time.
Originally I had tried doing this with an okHttp interceptor, however it didn't seem to work.

If I was to work further on the app, I would have a separate file containing all the Luas stop names which
will populate a dropdown to allow you to select the stop you want for Inbound and Outbound during the time
periods. I would also make the stop retrieval automatically switch a setting that can be toggled on or off.