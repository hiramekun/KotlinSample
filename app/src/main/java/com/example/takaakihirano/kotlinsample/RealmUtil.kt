package com.example.takaakihirano.kotlinsample

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by takaakihirano on 2017/04/18.
 */

fun getInstanceForDevelopment(): Realm =
        Realm.getInstance(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())

fun <T : RealmObject> covertListToRealmList(managedList: List<T>): RealmList<T> {
    val realmList: RealmList<T> = RealmList()
    realmList.addAll(managedList.subList(0, managedList.size))

    return realmList
}
