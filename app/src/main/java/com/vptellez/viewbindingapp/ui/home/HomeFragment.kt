package com.vptellez.viewbindingapp.ui.home

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vptellez.viewbindingapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    private var lastTotal = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeUiState()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(
            onAdd = viewModel::addProduct,
            onRemove = viewModel::removeProduct
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                productAdapter.submitList(state.products)
                animateTotal(state.totalItems)
            }
        }
    }

    private fun animateTotal(newTotal: Int) {
        val animator = ValueAnimator.ofInt(lastTotal, newTotal)
        animator.duration = 300
        animator.addUpdateListener { animation ->
            binding.tvTotal.text = "Total: ${animation.animatedValue as Int}"
        }
        animator.start()
        lastTotal = newTotal
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 🔥 evita memory leaks
    }
}