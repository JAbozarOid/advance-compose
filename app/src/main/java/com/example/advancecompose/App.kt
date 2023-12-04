package com.example.advancecompose

import android.app.Application
import timber.log.Timber

// @HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        val tree = if (BuildConfig.DEBUG) {
            Timber.DebugTree()
        } else {
            releaseTree
        }
        Timber.plant(tree)
    }

    private val releaseTree = object : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            logToCrashReportingTool(priority, message, t)
            super.log(priority, tag, message, t)
        }
    }

    private fun logToCrashReportingTool(priority: Int, message: String, t: Throwable?) {
        // TODO log to Crashlytics / Bugsnag / NewRelic / your fav tool
    }
}
