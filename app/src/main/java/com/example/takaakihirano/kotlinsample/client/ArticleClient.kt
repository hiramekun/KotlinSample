package com.example.takaakihirano.kotlinsample.client

import com.example.takaakihirano.kotlinsample.model.Article
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by takaakihirano on 2017/04/15.
 */

interface ArticleClient {

    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>
}