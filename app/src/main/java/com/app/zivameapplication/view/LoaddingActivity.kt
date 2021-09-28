package com.app.zivameapplication.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.zivameapplication.BaseActivity
import com.app.zivameapplication.R
import com.app.zivameapplication.launchActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_loadder.*

class LoaddingActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadder)
        Glide.with(this).load(R.raw.loadder).into(ivLoadder)
        Handler(Looper.getMainLooper()).postDelayed({
            launchActivity(OrderCompleteActivity::class.java,true)
        },30000)
    }
}