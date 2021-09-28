package com.app.zivameapplication.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.*
import com.app.zivameapplication.model.CartModel
import com.app.zivameapplication.model.Products
import com.app.zivameapplication.repository.HomeRepo
import com.app.zivameapplication.webservices.Status
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {


    private var badgeCounter: BadgeCounter? = null
    private var cartList= mutableListOf<CartModel>()
    private var productList= mutableListOf<Products>()
    private var productHomeItemRecyclerView:ProductHomeItemRecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        cartList.clear()
        badgeCounter= object : BadgeCounter {
            override fun setCounter() {
                setBadgeCount()
            }
        }
        productList.clear()
        productHomeItemRecyclerView=
            ProductHomeItemRecyclerView(productList,applicationContext,badgeCounter!!)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        rvHome.layoutManager = mLayoutManager
        rvHome.itemAnimator = DefaultItemAnimator()
        rvHome.addItemDecoration(
            MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16)
        )
        rvHome.adapter = productHomeItemRecyclerView
        if(!UtilsWithContext.isNetworkAvailable()){
            showToast("No connectivity,Please check your connection and try again.")
        }else{
            fetchDataFromServer()
        }
        ivCart?.setOnClickListener {
            launchActivity(CartActivity::class.java,true)
        }
    }

    private fun fetchDataFromServer() {
        HomeRepo.getGadgetsData().observe(this, Observer {
            when(it.status){
                Status.SUCCESS->{
                    if(it.data != null){
                        mShimmerViewContainer!!.stopShimmerAnimation()
                        productList.add(it.data!!)
                        if(productList.size>0){
                            productHomeItemRecyclerView?.notifyDataSetChanged()
                        }else{
                            rvHome?.gone()
                            tvEmptyText?.visible()
                        }
                    }
                }
                Status.FAILURE->{
                    showToast(getString(R.string.default_error))
                }
                Status.LOADING->{

                }
            }
        })
    }

    public override fun onResume() {
        super.onResume()
        mShimmerViewContainer!!.startShimmerAnimation()
    }

    public override fun onPause() {
        mShimmerViewContainer!!.stopShimmerAnimation()
        super.onPause()
    }

    private fun setBadgeCount() {
        cartList=getCartData()
        if(cartList.size>0){
            rlBadge?.visible()
            tvCount?.setText(cartList.size.toString())
        }else{
            rlBadge?.gone()
        }
    }
}