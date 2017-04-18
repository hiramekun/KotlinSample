package com.example.takaakihirano.kotlinsample

import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by takaakihirano on 2017/04/18.
 */

fun getInstanceForDevelopment(): Realm =
        Realm.getInstance(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())
