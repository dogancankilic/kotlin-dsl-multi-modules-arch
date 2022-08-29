package com.dogancan.kotlin_dsl_multi_modules_arch.features.character

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dogancan.core.base.platform.BaseFragment
import com.dogancan.core.base.platform.BaseViewModel
import com.dogancan.core.utils.binding.viewBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.R
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.FragmentCharacterBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.extensions.collects
import com.dogancan.kotlin_dsl_multi_modules_arch.features.character.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author dogancankilic
 * Created at 4.06.2022
 */
@AndroidEntryPoint
class CharacterFragment : BaseFragment() {
    private val viewModel: CharacterViewModel by viewModels()

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter()
    }
    private val binding: FragmentCharacterBinding by viewBinding()
    override fun initView() {
        binding.rvCharacters.adapter = characterAdapter

        if (characterAdapter.itemCount == 0) {
            viewModel.getCharacters()
        }

        viewModel.uiState.collects(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is CharacterViewModel.UiState.Success -> {
                    binding.progressBar.isVisible = false
                    characterAdapter.submitData(uiState.data)
                }
                is CharacterViewModel.UiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }

        characterAdapter.onItemClick = { item ->
            findNavController().navigate(CharacterFragmentDirections.navigateToCharacterDetail(item.id))
        }
    }

    override fun layoutRes(): Int = R.layout.fragment_character
    override fun viewModel(): BaseViewModel = viewModel
}
