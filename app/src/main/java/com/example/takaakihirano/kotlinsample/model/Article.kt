package com.example.takaakihirano.kotlinsample.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by takaakihirano on 2017/04/18.
 */

@RealmClass
public open class Article(@PrimaryKey public open var id: String = "",
                          public open var title: String = "",
                          public open var url: String = "",
                          public open var user: User? = null) : RealmObject()