package com.vptellez.viewbindingapp.ui.home

import com.vptellez.viewbindingapp.state.CartState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel()
    }

    @Test
    fun initialState_hasZeroTotalItems() = runTest {
        val state: CartState = viewModel.uiState.first()

        assertEquals(0, state.totalItems)
        assertEquals(3, state.products.size)
    }

    @Test
    fun addProduct_increasesQuantityAndTotal() = runTest {
        viewModel.addProduct(productId = 1)

        val state = viewModel.uiState.first()
        val product = state.products.first { it.id == 1 }

        assertEquals(1, product.quantity)
        assertEquals(1, state.totalItems)
    }

    @Test
    fun removeProduct_doesNotAllowNegativeQuantity() = runTest {
        viewModel.removeProduct(productId = 1)

        val state = viewModel.uiState.first()
        val product = state.products.first { it.id == 1 }

        assertEquals(0, product.quantity)
        assertEquals(0, state.totalItems)
    }

    @Test
    fun multipleProducts_updateTotalCorrectly() = runTest {
        viewModel.addProduct(1)
        viewModel.addProduct(1)
        viewModel.addProduct(2)

        val state = viewModel.uiState.first()

        assertEquals(3, state.totalItems)
    }
}