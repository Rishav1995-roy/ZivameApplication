package com.app.zivameapplication.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.*
import com.app.zivameapplication.databinding.ActivityCartBinding
import com.app.zivameapplication.model.CartModel

class CartActivity: BaseActivity() {

    private var productCartItemRecylerView:ProductCartItemRecylerView?=null
    private var cartList= mutableListOf<CartModel>()
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnCheckOut.gone()
        binding.ivBack.gone()
        cartList.clear()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.btnCheckOut.visible()
            binding.ivBack.visible()
            fetchDataFromCartTable()
        },6000)
        binding.btnCheckOut.setOnClickListener {
            deleteCartTable()
            launchActivity(LoaddingActivity::class.java,true)
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchDataFromCartTable() {
        cartList=getCartData()
        binding.mShimmerViewContainer.stopShimmerAnimation()
        binding.mShimmerViewContainer.gone()
        if(cartList.size>0){
            productCartItemRecylerView= ProductCartItemRecylerView(cartList,applicationContext)
            val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
            binding.rvCart.layoutManager = mLayoutManager
            binding.rvCart.itemAnimator = DefaultItemAnimator()
            binding.rvCart.addItemDecoration(
                MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16)
            )
            binding.rvCart.adapter = productCartItemRecylerView
        }else{
            binding.rvCart.gone()
            binding.tvEmptyCartText.visible()
        }
    }

    public override fun onResume() {
        super.onResume()
        binding.mShimmerViewContainer.startShimmerAnimation()
    }

    public override fun onPause() {
        binding.mShimmerViewContainer.stopShimmerAnimation()
        super.onPause()
    }
}