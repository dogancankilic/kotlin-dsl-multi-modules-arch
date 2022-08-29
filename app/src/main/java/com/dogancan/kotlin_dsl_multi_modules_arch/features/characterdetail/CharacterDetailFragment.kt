package com.dogancan.kotlin_dsl_multi_modules_arch.features.characterdetail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dogancan.core.base.platform.BaseFragment
import com.dogancan.core.base.platform.BaseViewModel
import com.dogancan.core.utils.binding.viewBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.CharacterArgs
import com.dogancan.kotlin_dsl_multi_modules_arch.R
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.FragmentCharacterDetailBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.extensions.collects
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment() {
    private val viewModel: CharacterDetailViewModel by viewModels()

    private val args by navArgs<CharacterArgs>()

    private val binding: FragmentCharacterDetailBinding by viewBinding()
    override fun initView() {
        viewModel.getCharacter(args.id)
        viewModel.uiState.collects(viewLifecycleOwner) { uiState ->

            when (uiState) {
                is CharacterDetailViewModel.UiState.Success -> {
                    binding.item = uiState.data
                }
            }
        }
    }

    override fun layoutRes(): Int = R.layout.fragment_character_detail
    override fun viewModel(): BaseViewModel = viewModel
}
