package com.example.coffeeshop.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coffeeshop.Domain.BannerModel
import com.example.coffeeshop.Domain.CategoryModel
import com.example.coffeeshop.Domain.ItemModel
import com.example.coffeeshop.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository = MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadCoffees(): LiveData<MutableList<ItemModel>> {
        return repository.loadCoffees()
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemModel>> {
        return repository.loadItemCategory(categoryId)
    }
}