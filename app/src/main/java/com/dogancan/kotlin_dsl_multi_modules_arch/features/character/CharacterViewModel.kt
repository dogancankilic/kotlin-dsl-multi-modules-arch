package com.dogancan.kotlin_dsl_multi_modules_arch.features.character

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dogancan.core.base.platform.BaseViewModel
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.domain.character.ICharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author dogancankilic
 * Created at 4.06.2022
 */
@HiltViewModel
class CharacterViewModel @Inject constructor(private val characterUseCase: ICharacterUseCase) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    fun getCharacters() {

        viewModelScope.launch {
            characterUseCase.getCharacters().characterList.cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    delay(2000)
                    updateUiState(pagingData)
                }
        }
    }

    private fun updateUiState(result: PagingData<CharacterUiModel>) {
        _uiState.update {
            UiState.Success(result)
        }
    }

    sealed interface UiState {
        data class Success(val data: PagingData<CharacterUiModel>) : UiState
        object Loading : UiState
    }
}
