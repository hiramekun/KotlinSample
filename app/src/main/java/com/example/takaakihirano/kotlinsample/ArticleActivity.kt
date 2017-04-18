package com.example.takaakihirano.kotlinsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.widget.ProgressBar
import com.example.takaakihirano.kotlinsample.model.Article
import io.realm.Realm

/**
 * Created by takaakihirano on 2017/04/15.
 */

class ArticleActivity : AppCompatActivity() {

    companion object {

        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, articleId: String): Intent =
                Intent(context, ArticleActivity::class.java).putExtra(ARTICLE_EXTRA, articleId)
    }

    var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val webView = findViewById(R.id.web_view) as WebView
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar
        val realm: Realm = getInstanceForDevelopment()
        val id: String = intent.extras.getString(ARTICLE_EXTRA)

        article = realm.where(Article::class.java).equalTo("id", id).findFirst()

        webView.loadUrl(article?.url)
        webView.setWebViewClient(MyWebViewClient(progressBar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val actionBar = supportActionBar as ActionBar

        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.title = article?.title

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }
}