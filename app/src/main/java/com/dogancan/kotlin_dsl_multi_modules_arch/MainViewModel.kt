package com.dogancan.kotlin_dsl_multi_modules_arch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel()
