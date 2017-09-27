package com.example.takaakihirano.kotlinsample

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import com.example.takaakihirano.kotlinsample.application.QiitaClientApp
import com.example.takaakihirano.kotlinsample.client.ArticleClient
import com.example.takaakihirano.kotlinsample.extensions.RealmService
import com.example.takaakihirano.kotlinsample.extensions.hideKeyboard
import com.example.takaakihirano.kotlinsample.extensions.toRealmList
import com.example.takaakihirano.kotlinsample.extensions.toast
import com.example.takaakihirano.kotlinsample.model.Article
import com.example.takaakihirano.kotlinsample.model.Self
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import org.jetbrains.anko.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit private var articleClient: ArticleClient

    private val listAdapter by lazy { ArticleListAdapter(applicationContext) }
    private val ui = MainActivityUi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)
        ui.setContentView(this)

        ui.listView.let {
            it.adapter = listAdapter
            it.setOnItemClickListener { _, _, position, _ ->
                ArticleActivity.intent(this, listAdapter.articles[position].id).let { startActivity(it) }
            }
            it.setOnTouchListener { _, event ->
                hideKeyboard(this)
                super.onTouchEvent(event)
            }
        }

        ui.searchButton.setOnClickListener {
            ui.progressBar.visibility = View.VISIBLE

            articleClient.search(ui.queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        ui.progressBar.visibility = View.GONE
                        hideKeyboard(this)
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        persist(it,
                                onSuccess = {
                                    ui.queryEditText.text.clear()
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

class MainActivityUi : AnkoComponent<MainActivity> {
    lateinit var listView: ListView
    lateinit var progressBar: ProgressBar
    lateinit var queryEditText: EditText
    lateinit var searchButton: Button

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        linearLayout {
            frameLayout {
                listView = listView().lparams(width = matchParent, height = matchParent)
                progressBar = progressBar {
                    visibility = View.GONE
                }.lparams(width = wrapContent, height = wrapContent, gravity = Gravity.CENTER)

            }.lparams(width = matchParent, height = 0, weight = 1F)

            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.BOTTOM

                queryEditText = editText().lparams(width = 0, height = wrapContent, weight = 1F)
                searchButton = button {
                    text = "検索"
                }.lparams(width = wrapContent, height = wrapContent)

            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}
