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
import com.example.takaakihirano.kotlinsample.extensions.bindView
import com.example.takaakihirano.kotlinsample.extensions.getInstanceForDevelopment
import com.example.takaakihirano.kotlinsample.model.Article
import com.example.takaakihirano.kotlinsample.util.MyWebViewClient
import io.realm.Realm

/**
 * Created by takaakihirano on 2017/04/15.
 */

private const val ARTICLE_EXTRA = "article"

class ArticleActivity : AppCompatActivity() {

    companion object {
        fun intent(context: Context, articleId: String): Intent =
                Intent(context, ArticleActivity::class.java).putExtra(ARTICLE_EXTRA, articleId)
    }

    private lateinit var article: Article

    private var realm: Realm? = null
    private val webView: WebView by bindView(R.id.web_view)
    private val progressBar: ProgressBar by bindView(R.id.progress_bar)
    private val id by lazy { intent.extras.getString(ARTICLE_EXTRA) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        realm = getInstanceForDevelopment()

        article = realm!!.where(Article::class.java).equalTo("id", id).findFirst()
        webView.loadUrl(article.url)
        webView.webViewClient = MyWebViewClient(progressBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        (supportActionBar as ActionBar).let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.title = article.title
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm?.close()
    }
}
