package com.app.zivameapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

/**
 * Shows toast message
 */
fun Activity.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun <T : BaseActivity> BaseActivity.launchActivity(
    activityClass: Class<T>,
    isFinishAffinity: Boolean = false,
    isSingleTop: Boolean = false,
    extras: Bundle.() -> Unit = {}
) {
    startActivity(Intent(this, activityClass).apply {
        putExtras(Bundle().apply(extras))
    })
    if (isFinishAffinity) {
        finishAffinity()
    }
}