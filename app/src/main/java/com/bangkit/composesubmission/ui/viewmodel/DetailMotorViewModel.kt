package com.bangkit.composesubmission.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.composesubmission.data.Repository
import com.bangkit.composesubmission.model.FavMotor
import com.bangkit.composesubmission.model.Motor
import com.bangkit.composesubmission.ui.components.StateUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailMotorViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _uiState: MutableStateFlow<StateUi<FavMotor>> =
        MutableStateFlow(StateUi.Loading)
    val uiState: StateFlow<StateUi<FavMotor>>
        get() = _uiState

    fun getPlayerById(playerId: Long) {
        viewModelScope.launch {
            _uiState.value = StateUi.Loading
            _uiState.value = StateUi.Success(repository.getMotorFavoriteById(playerId))
        }
    }

    fun addToCart(player: Motor, count: Int) {
        viewModelScope.launch {
            repository.updateOrderReward(player.id, count)
        }
    }
}