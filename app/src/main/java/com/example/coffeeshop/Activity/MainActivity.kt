package com.example.coffeeshop.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeeshop.Adapter.CategoryAdapter
import com.example.coffeeshop.Adapter.CoffeeAdapter
import com.example.coffeeshop.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initCategory()
        initCoffee()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)
            binding.progressBarBanner.visibility = View.GONE
        }
        viewModel.loadBanner()
    }

    private fun initCategory() {
        binding.progressBarCat.visibility = View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.recyclerViewCat.layoutManager =
                LinearLayoutManager(
                    this@MainActivity, LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCat.adapter = CategoryAdapter(it)
            binding.progressBarCat.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initCoffee() {
        binding.progressBarCoffees.visibility = View.VISIBLE
        viewModel.loadCoffees().observeForever {
            binding.recyclerViewCoffees.layoutManager = GridLayoutManager(this, 2)
            binding.recyclerViewCoffees.adapter = CoffeeAdapter(it)
            binding.progressBarCoffees.visibility = View.GONE
        }
        viewModel.loadCoffees()
    }
}