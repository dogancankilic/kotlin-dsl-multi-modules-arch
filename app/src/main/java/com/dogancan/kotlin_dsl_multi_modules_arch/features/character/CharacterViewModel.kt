package com.dogancan.kotlin_dsl_multi_modules_arch.features.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.domain.character.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
class CharacterViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun getCharacters() {
        viewModelScope.launch {
            characterUseCase.getCharacters().cachedIn(viewModelScope).collectLatest { pagingData ->

                _uiState.update {
                    it.copy(characters = pagingData)
                }
            }
        }
    }

    data class UiState(
        val characters: PagingData<CharacterUiModel> = PagingData.empty(),
    )
}
