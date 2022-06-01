package com.dogancan.kotlin_dsl_multi_modules_arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogancan.core.utils.Resource
import com.dogancan.domain.GetUserUseCase
import com.dogancan.domain.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCase: GetUserUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun getUser() {
        viewModelScope.launch {
            userUseCase.invoke().collectLatest { result ->
                _uiState.update {
                    it.copy(result)
                }
            }
        }
    }

    data class UiState(
        val userList: Resource<List<UserUiModel>?> = Resource.loading(emptyList())
    )
}
