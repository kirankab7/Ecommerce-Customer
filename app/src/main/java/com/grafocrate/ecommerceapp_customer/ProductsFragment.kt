package com.grafocrate.ecommerceapp_customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.grafocrate.ecommerceapp_customer.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ProductAdapter(
            onAddToCart = { product -> addToCart(product) },
            onBuyNow = { product -> buyNow(product) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner, Observer { products ->
            adapter.submitList(products)
        })
    }

    private fun addToCart(product: Product) {
        // Handle add to cart functionality
    }

    private fun buyNow(product: Product) {
        // Handle buy now functionality
        findNavController().navigate(R.id.action_productsFragment_to_orderListFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
