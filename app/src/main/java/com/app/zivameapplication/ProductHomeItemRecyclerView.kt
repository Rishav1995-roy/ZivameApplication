package com.app.zivameapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.databinding.RecyclerItemViewBinding
import com.app.zivameapplication.model.CartModel
import com.app.zivameapplication.model.Products
import com.app.zivameapplication.view.HomeActivity
import com.bumptech.glide.Glide

class ProductHomeItemRecyclerView(
    val productsList: MutableList<Products>,
    val context: Context,
    val badgeCounter: BadgeCounter,
    val homeActivity: HomeActivity,
) :
    RecyclerView.Adapter<ProductHomeItemRecyclerView.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
            .load(productsList[position].image_url)
            .centerCrop()
            .into(holder.binding.ivProduct)
        holder.binding.tvname.setText(productsList[position].name)
        holder.binding.tvprice.setText("₹" + productsList[position].price)
        holder.binding.tvrating.setText("(" + productsList[position].rating.toString() + ")")
        if (productsList[position].rating == 5) {
            holder.binding.ivfifthStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        } else if (productsList[position].rating == 4) {
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        } else if (productsList[position].rating == 3) {
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        } else if (productsList[position].rating == 2) {
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        } else if (productsList[position].rating == 1) {
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        } else if (productsList[position].rating == 0) {
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.empty_star)
        }
        holder.binding.btnAddToCart.setOnClickListener {
            val cartModel = CartModel(
                name = productsList[position].name,
                price = productsList[position].price,
                imageUrl = productsList[position].image_url,
                rating = productsList[position].rating,
            )
            homeActivity.addDataToCartTable(cartModel)
            badgeCounter.setCounter()
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}