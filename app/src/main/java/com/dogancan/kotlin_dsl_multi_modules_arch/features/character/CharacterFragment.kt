package com.dogancan.kotlin_dsl_multi_modules_arch.features.character

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dogancan.core.base.platform.BaseFragment
import com.dogancan.core.base.platform.BaseViewModel
import com.dogancan.core.utils.binding.viewBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.R
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.FragmentCharacterBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.features.character.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    characterAdapter.submitData(uiState.characters)
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
