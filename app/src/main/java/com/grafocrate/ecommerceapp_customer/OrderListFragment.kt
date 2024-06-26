package com.grafocrate.ecommerceapp_customer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.grafocrate.ecommerceapp_customer.databinding.FragmentOrderListBinding


class OrderListFragment : Fragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OrderAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.orderItems.observe(viewLifecycleOwner, Observer { orderItems ->
            adapter.submitList(orderItems)
            binding.totalAmount.text = "Total: ${orderItems.sumOf { it.price.toDouble() * it.quantity.toInt() }}"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
