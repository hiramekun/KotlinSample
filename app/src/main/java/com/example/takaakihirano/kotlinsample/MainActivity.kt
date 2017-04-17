package com.example.takaakihirano.kotlinsample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.example.takaakihirano.kotlinsample.client.ArticleClient
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as QiitaClientApp).component.inject(this)
        setContentView(R.layout.activity_main)

        /* set up listView and adapter */
        val listView: ListView = findViewById(R.id.list_view) as ListView
        val listAdapter = ArticleListAdapter(applicationContext)
        listView.adapter = listAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            ArticleActivity.intent(this, listAdapter.articles[position]).let { startActivity(it) }
        }

        /* set up search */
        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search_button) as Button
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー: $it")
                    })

        }
    }
}
