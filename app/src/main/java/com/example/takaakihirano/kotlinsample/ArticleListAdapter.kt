package com.example.takaakihirano.kotlinsample

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.takaakihirano.kotlinsample.model.Article
import com.example.takaakihirano.kotlinsample.view.ArticleView

/**
 * Created by takaakihirano on 2017/04/10.
 */

class ArticleListAdapter(private val context: Context) : BaseAdapter() {

    var articles: List<Article> = emptyList()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View =
            ((convertView as? ArticleView) ?: ArticleView(context)).apply {
                setArticle(articles[position])
            }

    override fun getItem(position: Int): Any? = articles[position]

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = articles.size

}