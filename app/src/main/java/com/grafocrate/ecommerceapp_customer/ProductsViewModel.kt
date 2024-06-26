package com.grafocrate.ecommerceapp_customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProductsViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val snapshot = Firebase.database.reference.child("products").get().await()
                val productList = snapshot.children.map { it.getValue(Product::class.java)!! }
                _products.postValue(productList)
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}
