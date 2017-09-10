package com.example.takaakihirano.kotlinsample.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by takaakihirano on 2017/04/18.
 */

@RealmClass
open class Self(@PrimaryKey open var id: Int = 0,
                       open var articles: RealmList<Article>? = null) : RealmObject()