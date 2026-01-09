package com.vptellez.viewbindingapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vptellez.viewbindingapp.databinding.ItemProductBinding
import com.vptellez.viewbindingapp.model.Product

class ProductAdapter(
    private val onAdd: (Int) -> Unit,
    private val onRemove: (Int) -> Unit
) : ListAdapter<Product, ProductAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(
        private val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.tvName.text = product.name
            binding.tvQuantity.text = product.quantity.toString()

            binding.btnMinus.isEnabled = product.quantity > 0
            binding.btnMinus.alpha = if (product.quantity > 0) 1f else 0.3f

            binding.btnPlus.setOnClickListener {
                onAdd(product.id)
            }

            binding.btnMinus.setOnClickListener {
                onRemove(product.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem == newItem
            }
        }
    }
}