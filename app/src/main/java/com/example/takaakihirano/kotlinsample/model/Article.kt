package com.example.takaakihirano.kotlinsample.model

/**
 * Created by takaakihirano on 2017/04/08.
 */

data class Article(val id: String,
                   val title: String,
                   val url: String,
                   val user: User)