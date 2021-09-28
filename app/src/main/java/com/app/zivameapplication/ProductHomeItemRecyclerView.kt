package com.app.zivameapplication

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.zivameapplication.model.Products
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recycler_item_view.view.*

class ProductHomeItemRecyclerView(val productsList:MutableList<Products>, val context: Context, val badgeCounter:BadgeCounter):
    RecyclerView.Adapter<ProductHomeItemRecyclerView.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
            .load(productsList[position].image_url)
            .centerCrop()
            .into(holder.itemView.ivProduct)
        holder.itemView.tvname!!.setText(productsList[position].name)
        holder.itemView.tvprice!!.setText("₹"+productsList[position].price)
        holder.itemView.tvrating!!.setText("("+productsList[position].rating.toString()+")")
        if(productsList[position].rating==5){
            holder.itemView.ivfifthStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivfourthStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivThirdStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivSecondStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivfirstStar!!.setImageResource(R.drawable.fill_star)
        }else if(productsList[position].rating==4){
            holder.itemView.ivfifthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfourthStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivThirdStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivSecondStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivfirstStar!!.setImageResource(R.drawable.fill_star)
        }else if(productsList[position].rating==3){
            holder.itemView.ivfifthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfourthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivThirdStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivSecondStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivfirstStar!!.setImageResource(R.drawable.fill_star)
        }else if(productsList[position].rating==2){
            holder.itemView.ivfifthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfourthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivThirdStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivSecondStar!!.setImageResource(R.drawable.fill_star)
            holder.itemView.ivfirstStar!!.setImageResource(R.drawable.fill_star)
        }else if(productsList[position].rating==1){
            holder.itemView.ivfifthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfourthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivThirdStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivSecondStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfirstStar!!.setImageResource(R.drawable.fill_star)
        }else if(productsList[position].rating==0){
            holder.itemView.ivfifthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfourthStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivThirdStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivSecondStar!!.setImageResource(R.drawable.empty_star)
            holder.itemView.ivfirstStar!!.setImageResource(R.drawable.empty_star)
        }
        holder.itemView.btnAddToCart.setOnClickListener {
            (context as BaseActivity).addDataToCartTable(productsList[position])
            badgeCounter.setCounter()
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}