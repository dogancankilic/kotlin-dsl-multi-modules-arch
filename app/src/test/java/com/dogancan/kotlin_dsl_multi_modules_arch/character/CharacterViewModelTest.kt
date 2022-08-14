package com.dogancan.kotlin_dsl_multi_modules_arch.character

import androidx.paging.PagingData
import app.cash.turbine.test
import com.dogancan.domain.character.CharacterListUiModel
import com.dogancan.domain.character.CharacterUiModel
import com.dogancan.domain.character.ICharacterUseCase
import com.dogancan.kotlin_dsl_multi_modules_arch.TestCoroutineRule
import com.dogancan.kotlin_dsl_multi_modules_arch.features.character.CharacterViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

/**
 * @author dogancankilic
 * Created on 9.08.2022
 */
@ExperimentalCoroutinesApi
class CharacterViewModelTest {
    private val pagingData = mockk<Flow<PagingData<CharacterUiModel>>>(relaxed = true)

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun `test character success state`() = testCoroutineRule.runTest {
        // Given
        val characterUseCase = object : ICharacterUseCase {
            override fun getCharacters(): CharacterListUiModel {
                return CharacterListUiModel(pagingData)
            }
        }
        val viewModel = CharacterViewModel(characterUseCase)

        // When
        characterUseCase.getCharacters().characterList.collectLatest { result ->

            // Then
            viewModel.uiState.test {
                Assert.assertEquals(result, awaitItem().characters)
            }
        }
    }
}
