package com.example.takaakihirano.kotlinsample.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by takaakihirano on 2017/04/18.
 */

@RealmClass
open class Article(@PrimaryKey open var id: String = "",
                          open var title: String = "",
                          open var url: String = "",
                          open var user: User? = null) : RealmObject()