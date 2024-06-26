package com.grafocrate.ecommerceapp_customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grafocrate.ecommerceapp_customer.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductAdapter(private val onAddToCart: (Product) -> Unit, private val onBuyNow: (Product) -> Unit) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, onAddToCart, onBuyNow)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val onAddToCart: (Product) -> Unit,
        private val onBuyNow: (Product) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.productName.text = product.name
            binding.productQuantity.text = product.quantity
            binding.productPrice.text = product.price
            Picasso.get().load(product.imageUrl).into(binding.productImage)
            binding.addToCartButton.setOnClickListener { onAddToCart(product) }
            binding.buyNowButton.setOnClickListener { onBuyNow(product) }
        }
    }
}
class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}
