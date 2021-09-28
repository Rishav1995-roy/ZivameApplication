package com.app.zivameapplication.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.*
import com.app.zivameapplication.model.CartModel
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_cart.mShimmerViewContainer
import kotlinx.android.synthetic.main.activity_home.*

class CartActivity: BaseActivity() {

    private var productCartItemRecylerView:ProductCartItemRecylerView?=null
    private var cartList= mutableListOf<CartModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        cartList.clear()
        productCartItemRecylerView=
            ProductCartItemRecylerView(cartList,applicationContext)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        rvCart.layoutManager = mLayoutManager
        rvCart.itemAnimator = DefaultItemAnimator()
        rvCart.addItemDecoration(
            MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16)
        )
        rvCart.adapter = productCartItemRecylerView
        Handler(Looper.getMainLooper()).postDelayed({
            fetchDataFromCartTable()
        },5000)
        btnCheckOut?.setOnClickListener {
            deleteCartTable()
            launchActivity(LoaddingActivity::class.java,true)
        }
        ivBack?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchDataFromCartTable() {
        cartList=getCartData()
        mShimmerViewContainer!!.stopShimmerAnimation()
        if(cartList.size>0){
            productCartItemRecylerView?.notifyDataSetChanged()
        }else{
            rvCart?.gone()
            tvEmptyCartText?.visible()
        }
    }

    public override fun onResume() {
        super.onResume()
        mShimmerViewContainer!!.startShimmerAnimation()
    }

    public override fun onPause() {
        mShimmerViewContainer!!.stopShimmerAnimation()
        super.onPause()
    }
}