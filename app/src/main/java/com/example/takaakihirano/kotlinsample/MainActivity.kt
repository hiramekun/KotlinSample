package com.example.takaakihirano.kotlinsample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.example.takaakihirano.kotlinsample.application.QiitaClientApp
import com.example.takaakihirano.kotlinsample.client.ArticleClient
import com.example.takaakihirano.kotlinsample.extensions.*
import com.example.takaakihirano.kotlinsample.model.Article
import com.example.takaakihirano.kotlinsample.model.Self
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    private val listView: ListView by bindView(R.id.list_view)
    private val listAdapter by lazy { ArticleListAdapter(applicationContext) }

    private val queryEditText: EditText by bindView(R.id.query_edit_text)
    private val searchButton: Button by bindView(R.id.search_button)
    private val progressBar: ProgressBar by bindView(R.id.progress_bar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as QiitaClientApp).component.inject(this)

        listView.adapter = listAdapter
        listView.setOnItemClickListener { _, _, position, _ ->
            ArticleActivity.intent(this, listAdapter.articles[position].id).let { startActivity(it) }
        }

        listView.setOnTouchListener { _, event ->
            hideKeyboard(this)
            super.onTouchEvent(event)
        }

        searchButton.setOnClickListener { _ ->
            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                        hideKeyboard(this)
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        persist(it,
                                onSuccess = {
                                    queryEditText.text.clear()
                                    updateListView(it)
                                }
                        )
                    }) {
                        toast("エラー: $it")
                    }
        }
    }

    private fun persist(articles: List<Article>, onSuccess: () -> Unit) {
        RealmService.executeTransactionAsync({ realm ->
            realm.where(Article::class.java).findAll()?.deleteAllFromRealm()
            (realm.where(Self::class.java).findFirst() ?: realm.createObject(Self::class.java, 0))
                    .articles = realm.copyToRealmOrUpdate(articles).toRealmList()

        }, onSuccess)
    }

    private fun updateListView(articles: List<Article>) {
        listAdapter.articles = articles
        listAdapter.notifyDataSetChanged()
    }
}
