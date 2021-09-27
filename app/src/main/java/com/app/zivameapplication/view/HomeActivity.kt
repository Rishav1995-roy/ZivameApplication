package com.app.zivameapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.zivameapplication.BaseActivity
import com.app.zivameapplication.R

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}