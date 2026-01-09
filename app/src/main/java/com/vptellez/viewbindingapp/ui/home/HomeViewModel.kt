package com.vptellez.viewbindingapp.ui.home

import androidx.lifecycle.ViewModel
import com.vptellez.viewbindingapp.model.Product
import com.vptellez.viewbindingapp.state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val initialProducts = listOf(
        Product(id = 1, name = "Café"),
        Product(id = 2, name = "Té"),
        Product(id = 3, name = "Galletas")
    )

    private val _uiState = MutableStateFlow(
        CartState(products = initialProducts)
    )
    val uiState: StateFlow<CartState> = _uiState

    fun addProduct(productId: Int) {
        updateQuantity(productId, delta = +1)
    }

    fun removeProduct(productId: Int) {
        updateQuantity(productId, delta = -1)
    }

    private fun updateQuantity(productId: Int, delta: Int) {
        _uiState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productId) {
                    val newQuantity = (product.quantity + delta).coerceAtLeast(0)
                    product.copy(quantity = newQuantity)
                } else {
                    product
                }
            }

            currentState.copy(
                products = updatedProducts,
                totalItems = updatedProducts.sumOf { it.quantity }
            )
        }
    }
}