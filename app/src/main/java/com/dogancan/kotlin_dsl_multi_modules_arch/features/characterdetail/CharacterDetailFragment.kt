package com.dogancan.kotlin_dsl_multi_modules_arch.features.characterdetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.dogancan.core.utils.Resource
import com.dogancan.core.utils.bindViewBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.CharacterArgs
import com.dogancan.kotlin_dsl_multi_modules_arch.R
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @author dogancankilic
 * Created at 5.06.2022
 */
@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private val viewModel: CharacterDetailViewModel by viewModels()

    private val args by navArgs<CharacterArgs>()

    private val binding by bindViewBinding<FragmentCharacterDetailBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacter(args.id)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state.character.status) {
                        Resource.Status.SUCCESS -> {
                            binding.tvName.text = state.character.data?.name
                        }
                        Resource.Status.ERROR -> {
                            Log.d("CharacterDetailTag", state.character.message.toString())
                        }
                        Resource.Status.LOADING -> {
                            Log.d("CharacterDetailTag", "isLoading")
                        }
                    }
                }
            }
        }
    }
}
