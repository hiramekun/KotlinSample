package com.example.takaakihirano.kotlinsample.extensions

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by takaakihirano on 2017/04/18.
 */

fun getInstanceForDevelopment(): Realm =
        Realm.getInstance(RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build())

fun <T : RealmObject> List<T>.toRealmList(): RealmList<T> =
        RealmList<T>().also {
            it.addAll(this.subList(0, this.size))
        }

object RealmService {
    fun executeTransactionAsync(transaction: (Realm) -> Unit, onSuccess: () -> Unit) {
        val realm = getInstanceForDevelopment()
        realm.executeTransactionAsync(transaction, {
            realm.close()
            onSuccess()
        })
    }
}
