package com.example.takaakihirano.kotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.takaakihirano.kotlinsample.model.Article
import com.example.takaakihirano.kotlinsample.model.User
import com.example.takaakihirano.kotlinsample.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val articleView = ArticleView(applicationContext)

        articleView.setArticle(Article(id = "123",
                title = "Kotlin入門",
                url = "http://www.example.com/articles/123",
                user = User(id = "456", name = "太郎", profileImageUrl = "")))

        setContentView(articleView)
    }
}
