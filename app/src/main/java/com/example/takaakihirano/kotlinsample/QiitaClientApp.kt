package com.example.takaakihirano.kotlinsample

import android.app.Application
import com.example.takaakihirano.kotlinsample.dagger.AppComponent
import com.example.takaakihirano.kotlinsample.dagger.DaggerAppComponent
import io.realm.Realm

/**
 * Created by takaakihirano on 2017/04/17.
 */

class QiitaClientApp : Application() {

    val component: AppComponent by lazy {

        // init Realm
        Realm.init(this)

        DaggerAppComponent.create()
    }
}