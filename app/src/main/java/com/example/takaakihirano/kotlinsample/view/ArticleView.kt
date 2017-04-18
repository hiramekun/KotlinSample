package com.example.takaakihirano.kotlinsample.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.takaakihirano.kotlinsample.R
import com.example.takaakihirano.kotlinsample.extensions.bindView
import com.example.takaakihirano.kotlinsample.model.Article

/**
 * Created by takaakihirano on 2017/04/10.
 */

class ArticleView : FrameLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?,
                attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?,
                attrs: AttributeSet?,
                defStyleAttr: Int,
                defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    val profileImageView: ImageView by bindView<ImageView>(R.id.profile_image)

    val titleTextView: TextView by bindView<TextView>(R.id.title)

    val userNameTextView: TextView by bindView<TextView>(R.id.user_name)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(article: Article) {
        titleTextView.text = article.title
        userNameTextView.text = article.user?.name
        Glide.with(context).load(article.user?.profileImageUrl).into(profileImageView)
    }
}