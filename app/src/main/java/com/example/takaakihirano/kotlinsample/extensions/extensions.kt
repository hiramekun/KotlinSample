package com.example.takaakihirano.kotlinsample.extensions

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * Created by takaakihirano on 2017/04/10.
 */

@Suppress("UNCHECKED_CAST")
fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.hideKeyboard(activity: AppCompatActivity) {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
}
