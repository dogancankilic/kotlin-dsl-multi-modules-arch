package com.dogancan.core.base.platform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * @author dogancankilic
 * Created on 19.06.2022
 */
abstract class BaseViewModel : ViewModel() {

    private val _isLoading = Channel<Boolean>()
    val isLoading = _isLoading.receiveAsFlow()

    open suspend fun setLoadingState(state: Boolean) {
        _isLoading.send(state)
    }

    suspend fun <T> invokeUseCase(
        flow: Flow<Result<T>>,
        onSuccess: ((data: T) -> Unit)? = null,
        onError: ((t: Throwable) -> Unit)? = null,
        showLoading: Boolean = true
    ) {
        setLoadingState(showLoading)

        viewModelScope.launch {
            flow.collectLatest {
                it.fold(onSuccess = { data ->
                    delay(3000)
                    if (showLoading) setLoadingState(false)
                    onSuccess?.invoke(data)
                }, onFailure = { error ->
                    if (showLoading) setLoadingState(false)
                    onError?.invoke(error)
                })
            }
        }
    }
}
