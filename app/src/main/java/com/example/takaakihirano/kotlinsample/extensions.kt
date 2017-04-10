package com.example.takaakihirano.kotlinsample

import android.support.annotation.IdRes
import android.view.View

/**
 * Created by takaakihirano on 2017/04/10.
 */

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}
