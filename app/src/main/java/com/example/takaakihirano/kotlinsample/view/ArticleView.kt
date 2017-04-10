package com.example.takaakihirano.kotlinsample.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.takaakihirano.kotlinsample.R
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

    var profileImageView: ImageView? = null

    var titleTextView: TextView? = null

    var userNameTextView: TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)

        profileImageView = findViewById(R.id.profile_image) as ImageView

        titleTextView = findViewById(R.id.title) as TextView

        userNameTextView = findViewById(R.id.user_name) as TextView
    }

    fun setArticle(article: Article) {
        titleTextView?.text = article.title
        userNameTextView?.text = article.user.name

        // TODO: set profile image
        profileImageView?.setBackgroundColor(Color.RED)
    }
}