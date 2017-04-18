package com.example.takaakihirano.kotlinsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import com.example.takaakihirano.kotlinsample.model.Article

/**
 * Created by takaakihirano on 2017/04/15.
 */

class ArticleActivity : AppCompatActivity() {

    companion object {

        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java).putExtra(ARTICLE_EXTRA, article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val webView = findViewById(R.id.web_view) as WebView
        val article: Article = intent.getParcelableExtra(ARTICLE_EXTRA)

        webView.loadUrl(article.url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        (supportActionBar as ActionBar).setDisplayHomeAsUpEnabled(true)
        (supportActionBar as ActionBar).setDisplayShowHomeEnabled(true)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}