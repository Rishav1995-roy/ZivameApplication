package com.app.zivameapplication.view

import android.content.Intent
import android.os.Bundle
import com.app.zivameapplication.BaseActivity
import com.app.zivameapplication.R
import kotlinx.android.synthetic.main.activity_order_complete.*

class OrderCompleteActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_complete)
        btnContinue?.setOnClickListener {
            applicationContext.startActivity(
                Intent(
                    applicationContext,
                    HomeActivity::class.java
                ).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra("FinishAndRestart", true)
                })
        }
    }
}