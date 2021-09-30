package com.app.zivameapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.databinding.RecyclerItemViewBinding
import com.app.zivameapplication.model.CartModel
import com.bumptech.glide.Glide

class ProductCartItemRecylerView(val cartList:MutableList<CartModel>, val context: Context):
    RecyclerView.Adapter<ProductCartItemRecylerView.MyViewHolder>() {

    inner class MyViewHolder(val binding: RecyclerItemViewBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerItemViewBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.btnAddToCart.gone()
        Glide.with(context)
            .load(cartList[position].imageUrl)
            .centerCrop()
            .into(holder.binding.ivProduct)
        holder.binding.tvname.setText(cartList[position].name)
        holder.binding.tvprice.setText("₹"+cartList[position].price)
        holder.binding.tvrating.setText("("+cartList[position].rating.toString()+")")
        if(cartList[position].rating==5){
            holder.binding.ivfifthStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        }else if(cartList[position].rating==4){
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        }else if(cartList[position].rating==3){
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        }else if(cartList[position].rating==2){
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.fill_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        }else if(cartList[position].rating==1){
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.fill_star)
        }else if(cartList[position].rating==0){
            holder.binding.ivfifthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfourthStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivThirdStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivSecondStar.setImageResource(R.drawable.empty_star)
            holder.binding.ivfirstStar.setImageResource(R.drawable.empty_star)
        }
    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}