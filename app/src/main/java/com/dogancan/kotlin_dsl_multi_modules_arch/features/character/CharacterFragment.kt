package com.dogancan.kotlin_dsl_multi_modules_arch.features.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dogancan.core.utils.bindViewBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.R
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.FragmentCharacterBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.features.character.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author dogancankilic
 * Created at 4.06.2022
 */
@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {
    private val viewModel: CharacterViewModel by viewModels()

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }

    private val binding by bindViewBinding<FragmentCharacterBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCharacters.adapter = characterAdapter

        if (characterAdapter.itemCount == 0) {
            viewModel.getCharacters()
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    characterAdapter.submitData(uiState.characters)
                }
            }
        }

        characterAdapter.onItemClick = { item ->
            findNavController().navigate(CharacterFragmentDirections.navigateToCharacterDetail(item.id))
        }
    }
}
