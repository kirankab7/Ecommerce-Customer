package com.grafocrate.ecommerceapp_customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderListViewModel : ViewModel() {

    private val _orderItems = MutableLiveData<List<OrderItem>>()
    val orderItems: LiveData<List<OrderItem>> get() = _orderItems

    init {
        fetchOrderItems()
    }

    private fun fetchOrderItems() {
        // Fetch order items from Firebase or other data source
        // For demo, we will use hardcoded data
        _orderItems.value = listOf(
            OrderItem("Product 1", "1", "10.0"),
            OrderItem("Product 2", "2", "15.0")
        )
    }
}

data class OrderItem(val name: String, val quantity: String, val price: String)
