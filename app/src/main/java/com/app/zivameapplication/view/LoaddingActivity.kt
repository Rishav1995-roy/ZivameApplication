package com.app.zivameapplication.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.zivameapplication.BaseActivity
import com.app.zivameapplication.R
import com.app.zivameapplication.databinding.ActivityCartBinding
import com.app.zivameapplication.databinding.ActivityLoadderBinding
import com.app.zivameapplication.launchActivity
import com.bumptech.glide.Glide

class LoaddingActivity: BaseActivity() {

    private lateinit var binding: ActivityLoadderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadderBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Glide.with(this).load(R.raw.loadder).into(binding.ivLoadder)
        Handler(Looper.getMainLooper()).postDelayed({
            launchActivity(OrderCompleteActivity::class.java,true)
        },30000)
    }
}