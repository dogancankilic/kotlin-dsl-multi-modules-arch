package com.dogancan.kotlin_dsl_multi_modules_arch.features.episode

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dogancan.core.utils.binding.viewBinding
import com.dogancan.kotlin_dsl_multi_modules_arch.R
import com.dogancan.kotlin_dsl_multi_modules_arch.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author dogancankilic
 * Created at 4.06.2022
 */
@AndroidEntryPoint
class EpisodeFragment : Fragment(R.layout.fragment_episode) {

    private val binding: FragmentEpisodeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
