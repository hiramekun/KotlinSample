package com.example.takaakihirano.kotlinsample.dagger

import com.example.takaakihirano.kotlinsample.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by takaakihirano on 2017/04/17.
 */

@Component(modules = [ClientModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}