package com.bangkit.composesubmission.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.composesubmission.data.Repository
import com.bangkit.composesubmission.model.FavMotor
import com.bangkit.composesubmission.ui.components.StateUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _uiState: MutableStateFlow<StateUi<List<FavMotor>>> =
        MutableStateFlow(StateUi.Loading)
    val uiState: StateFlow<StateUi<List<FavMotor>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllMotor()
                .catch {
                    _uiState.value = StateUi.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = StateUi.Success(orderRewards)
                }
        }
    }
}