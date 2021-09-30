package com.app.zivameapplication.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.*
import com.app.zivameapplication.databinding.ActivityHomeBinding
import com.app.zivameapplication.model.CartModel
import com.app.zivameapplication.model.Products
import com.app.zivameapplication.repository.HomeRepo
import com.app.zivameapplication.webservices.Status

class HomeActivity : BaseActivity() {


    private var badgeCounter: BadgeCounter? = null
    private var cartList= mutableListOf<CartModel>()
    private var productList= mutableListOf<Products>()
    private var productHomeItemRecyclerView:ProductHomeItemRecyclerView?=null
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        cartList.clear()
        badgeCounter= object : BadgeCounter {
            override fun setCounter() {
                setBadgeCount()
            }
        }
        productList.clear()
        if(!UtilsWithContext.isNetworkAvailable()){
            showToast("No connectivity,Please check your connection and try again.")
        }else{
            fetchDataFromServer()
        }
        binding.ivCart.setOnClickListener {
            launchActivity(CartActivity::class.java,true)
        }
        setBadgeCount()
    }

    private fun fetchDataFromServer() {
        HomeRepo.getGadgetsData().observe(this, Observer {
            when(it.status){
                Status.SUCCESS->{
                    if(it.data != null){
                        binding.mShimmerViewContainer.stopShimmerAnimation()
                        binding.mShimmerViewContainer.gone()
                        if(it.data!!.products.size>0){
                            productHomeItemRecyclerView=
                                ProductHomeItemRecyclerView(it.data!!.products,applicationContext,badgeCounter!!,this)
                            val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
                            binding.rvHome.layoutManager = mLayoutManager
                            binding.rvHome.itemAnimator = DefaultItemAnimator()
                            binding.rvHome.addItemDecoration(
                                MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16)
                            )
                            binding.rvHome.adapter = productHomeItemRecyclerView
                        }else{
                            binding.rvHome.gone()
                            binding.tvEmptyText.visible()
                        }
                    }
                }
                Status.FAILURE->{
                    binding.mShimmerViewContainer.stopShimmerAnimation()
                    binding.mShimmerViewContainer.gone()
                    showToast(getString(R.string.default_error))
                }
                Status.LOADING->{

                }
            }
        })
    }

    public override fun onResume() {
        super.onResume()
        binding.mShimmerViewContainer.startShimmerAnimation()
    }

    public override fun onPause() {
        binding.mShimmerViewContainer.stopShimmerAnimation()
        super.onPause()
    }

    private fun setBadgeCount() {
        cartList=getCartData()
        if(cartList.size>0){
            binding.rlBadge.visible()
            binding.tvCount.setText(cartList.size.toString())
        }else{
            binding.rlBadge.gone()
        }
    }
}