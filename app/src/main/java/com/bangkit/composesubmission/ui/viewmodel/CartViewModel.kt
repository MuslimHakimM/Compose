package com.bangkit.composesubmission.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.composesubmission.data.Repository
import com.bangkit.composesubmission.ui.components.StateUi
import com.bangkit.composesubmission.ui.screen.chart.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _uiState: MutableStateFlow<StateUi<CartState>> = MutableStateFlow(StateUi.Loading)
    val uiState: StateFlow<StateUi<CartState>>
        get() = _uiState

    fun getAddedOrderRewards() {
        viewModelScope.launch {
            _uiState.value = StateUi.Loading
            repository.getAddedOrderRewards()
                .collect { orderReward ->

                    _uiState.value = StateUi.Success(CartState(orderReward))
                }
        }
    }

    fun updateOrderReward(playerId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(playerId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderRewards()
                    }
                }
        }
    }
}