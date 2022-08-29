package com.dogancan.kotlin_dsl_multi_modules_arch.characterdetail

import app.cash.turbine.test
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.domain.characterdetail.ICharacterDetailUseCase
import com.dogancan.kotlin_dsl_multi_modules_arch.TestCoroutineRule
import com.dogancan.kotlin_dsl_multi_modules_arch.features.characterdetail.CharacterDetailViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * @author dogancankilic
 * Created on 7.08.2022
 */
@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest {

    private val characterUiModel = mockk<CharacterUiModel>(relaxed = true)

    private val error = mockk<Throwable>(relaxed = true)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `test character detail success state`() = testCoroutineRule.runTest {
        // Given
        val characterDetailUseCase = object : ICharacterDetailUseCase {
            override suspend fun invoke(id: Int): Flow<Result<CharacterUiModel>> = flow {
                emit(Result.success(characterUiModel))
            }
        }
        val viewModel = CharacterDetailViewModel(characterDetailUseCase)
        // When
        characterDetailUseCase.invoke(1).collect { result ->
            result.fold(
                onSuccess = { uiModel ->
                    viewModel.updateUiState(uiModel)
                },
                onFailure = {}
            )
            // Then
            viewModel.uiState.test {
                assertEquals(
                    result.getOrNull()
                        ?.let { CharacterDetailViewModel.UiState.Success(it) },
                    awaitItem()
                )
            }
        }
    }

    @Test
    fun `test character detail error state`() = testCoroutineRule.runTest {
        // Given
        var errorState = Throwable()
        val characterDetailUseCase = object : ICharacterDetailUseCase {
            override suspend fun invoke(id: Int): Flow<Result<CharacterUiModel>> = flow {
                emit(Result.failure(error))
            }
        }
        // When
        characterDetailUseCase.invoke(1).collectLatest { result ->
            result.fold(
                onSuccess = { },
                onFailure = { errorState = it }
            )
            // Then
            assertEquals(result.exceptionOrNull(), errorState)
        }
    }
}
