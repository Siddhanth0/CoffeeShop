package com.example.coffeeshop.Adapter

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffeeshop.Domain.ItemModel
import com.example.coffeeshop.Helper.ChangeNumberItemsListener
import com.example.coffeeshop.Helper.ManagementCart
import com.example.coffeeshop.databinding.ViewholderCartBinding

class CartAdapter(
    private val listItemSelected: ArrayList<ItemModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener?= null
): RecyclerView.Adapter<CartAdapter.Viewholder>() {

    inner class Viewholder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)

    private val managementCart = ManagementCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = listItemSelected.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.titleText.text = item.title
        holder.binding.feeEachItem.text = "₹${item.price * 15}"
        holder.binding.totalEach.text = "₹${Math.round(item.numberInCart * item.price) * 15}"
        holder.binding.numberEachItem.text = item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.mainImage)

        holder.binding.plusEachItem.setOnClickListener {
            managementCart.plusItem(listItemSelected, position, object: ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            } )
        }

        holder.binding.minusEachItem.setOnClickListener {
            managementCart.minusItem(listItemSelected, position, object: ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            } )
        }
        holder.binding.removeItemButton.setOnClickListener {
            managementCart.removeItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

    }

}