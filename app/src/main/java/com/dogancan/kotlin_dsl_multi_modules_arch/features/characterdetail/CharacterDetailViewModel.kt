package com.dogancan.kotlin_dsl_multi_modules_arch.features.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogancan.core.utils.Resource
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.domain.characterdetail.CharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterDetailUseCase: CharacterDetailUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            characterDetailUseCase.invoke(id).collectLatest { resource ->
                _uiState.update {
                    it.copy(character = resource)
                }
            }
        }
    }

    data class UiState(
        val character: Resource<CharacterUiModel?> = Resource.loading(null)
    )
}
