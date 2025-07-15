package com.example.coffeeshop.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.Adapter.CartAdapter
import com.example.coffeeshop.Helper.ChangeNumberItemsListener
import com.example.coffeeshop.Helper.ManagementCart
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityCartBinding
import com.example.coffeeshop.databinding.ViewholderCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagementCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managementCart = ManagementCart(this)
        calculateCart()
        setVariable()
        initCartList()
    }

    private fun setVariable() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun initCartList() {
        binding.apply {
            cartView.layoutManager = LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
            cartView.adapter = CartAdapter(
                managementCart.getListCart(),
                this@CartActivity,
                object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        calculateCart()
                    }

                }
            )
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15
        tax = Math.round((managementCart.getTotalFee()*15 * percentTax) * 100)/100.0
        val total = Math.round((managementCart.getTotalFee()*15 + tax + delivery) * 100)/100
        val itemTotal = Math.round(managementCart.getTotalFee() * 100) * 15/ 100
        binding.apply {
            totalFeeText.text = "₹${itemTotal}"
            taxText.text = "₹$tax"
            deliveryFeeText.text = "₹$delivery"
            totalText.text = "₹$total"
        }
    }
}