package com.example.takaakihirano.kotlinsample

import android.app.Application
import com.example.takaakihirano.kotlinsample.dagger.AppComponent
import com.example.takaakihirano.kotlinsample.dagger.DaggerAppComponent

/**
 * Created by takaakihirano on 2017/04/17.
 */

class QiitaClientApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}