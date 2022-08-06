package com.dogancan.kotlin_dsl_multi_modules_arch.features.characterdetail

import androidx.lifecycle.viewModelScope
import com.dogancan.core.base.platform.BaseViewModel
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.domain.characterdetail.CharacterDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(private val characterDetailUseCase: CharacterDetailUseCase) :
    BaseViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            invokeUseCase(
                characterDetailUseCase.invoke(id),
                onSuccess = { result ->
                    _uiState.update {
                        it.copy(character = result)
                    }
                }
            )
        }
    }

    data class UiState(
        val character: CharacterUiModel? = null
    )
}
