package com.example.takaakihirano.kotlinsample.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by takaakihirano on 2017/04/08.
 */

@RealmClass
public open class User(@PrimaryKey public open var id: String = "",
                       public open var name: String = "",
                       public open var profileImageUrl: String = "") : RealmObject()

