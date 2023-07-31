package com.uns.testitemtouchhelper

import android.app.Application
import android.content.Context
import com.uns.testitemtouchhelper.util.PreferenceUtil

class MyApp: Application() {

  init { instance = this }

  companion object {
    lateinit var prefs: PreferenceUtil

    private lateinit var instance: MyApp
    fun getContext() : Context = instance.applicationContext

  }

  override fun onCreate() {
    prefs = PreferenceUtil(applicationContext)
    super.onCreate()
  }

}