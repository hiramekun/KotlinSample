package com.example.takaakihirano.kotlinsample.dagger

import com.example.takaakihirano.kotlinsample.client.ArticleClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by takaakihirano on 2017/04/17.
 */

private const val QIITA = "https://qiita.com"

@Module
class ClientModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl(QIITA)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideArticleClient(retrofit: Retrofit): ArticleClient =
            retrofit.create(ArticleClient::class.java)
}