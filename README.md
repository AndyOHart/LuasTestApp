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
