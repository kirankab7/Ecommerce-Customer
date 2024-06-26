package com.grafocrate.ecommerceapp_customer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel :ViewModel() {
    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        loadCategories()
    }

    private fun loadCategories() {
        val categoryList = listOf(
            Category("Vegetables", R.drawable.ic_vegetables),
            Category("Fruits", R.drawable.ic_fruits),
            Category("Dairy", R.drawable.ic_dairy),
            Category("Toiletries", R.drawable.ic_toiletries),
            Category("Cereals", R.drawable.ic_cereals),
            Category("Pulses", R.drawable.ic_pulses),
            Category("Instant Foods", R.drawable.ic_instant_foods),
            Category("Vessels", R.drawable.ic_vessels),
            Category("Oils", R.drawable.ic_oils),
            Category("Drinks", R.drawable.ic_drinks)
        )
        _categories.postValue(categoryList)
    }
}
