package com.example.coffeeshop.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.coffeeshop.Domain.ItemModel
import com.example.coffeeshop.Helper.ManagementCart
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemModel
    private lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)
        bundle()
        initSizeList()
    }

    private fun initSizeList() {
        binding.apply {
            smallButton.setOnClickListener {
                smallButton.setBackgroundResource(R.drawable.stroke_brown_bg)
                mediumButton.setBackgroundResource(0)
                largeButton.setBackgroundResource(0)
            }
            mediumButton.setOnClickListener {
                smallButton.setBackgroundResource(0)
                mediumButton.setBackgroundResource(R.drawable.stroke_brown_bg)
                largeButton.setBackgroundResource(0)
            }
            largeButton.setOnClickListener {
                smallButton.setBackgroundResource(0)
                mediumButton.setBackgroundResource(0)
                largeButton.setBackgroundResource(R.drawable.stroke_brown_bg)
            }
        }
    }

    private fun bundle() {
        binding.apply {
            item = intent.getSerializableExtra(
                "object"
            ) as ItemModel
            Glide.with(this@DetailActivity)
                .load(item.picUrl[0])
                .into(binding.picMain)
            titleText.text = item.title
            description.text = item.description
            priceText.text = "₹" + item.price * 15
            ratingtxt.text = item.rating.toString()
            addToCartButton.setOnClickListener {
                item.numberInCart = Integer.valueOf(
                    numberText.text.toString()
                )
                managementCart.insertItems(item)
            }
            backButton.setOnClickListener {
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }
            plusButton.setOnClickListener {
                numberText.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }
            minusButton.setOnClickListener {
                if(item.numberInCart > 0) {
                    numberText.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }
        }
    }

}