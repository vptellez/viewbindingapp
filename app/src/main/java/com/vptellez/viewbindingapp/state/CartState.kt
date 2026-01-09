package com.vptellez.viewbindingapp.state

import com.vptellez.viewbindingapp.model.Product

data class CartState(
    val products: List<Product> = emptyList(),
    val totalItems: Int = 0
)