package com.example.takaakihirano.kotlinsample.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by takaakihirano on 2017/04/08.
 */

@RealmClass
open class User(@PrimaryKey open var id: String = "",
                       open var name: String = "",
                       open var profileImageUrl: String = "") : RealmObject()

