package com.app.zivameapplication.view

import android.content.Intent
import android.os.Bundle
import com.app.zivameapplication.BaseActivity
import com.app.zivameapplication.databinding.ActivityOrderCompleteBinding

class OrderCompleteActivity:BaseActivity() {

    private lateinit var binding: ActivityOrderCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOrderCompleteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnContinue.setOnClickListener {
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