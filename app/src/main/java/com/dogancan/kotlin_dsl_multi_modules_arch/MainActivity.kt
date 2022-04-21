package com.dogancan.kotlin_dsl_multi_modules_arch

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dogancan.core.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getUser()
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collectLatest { state ->
                when (state.userList.status) {
                    Resource.Status.SUCCESS -> {
                        Log.d("MainActivityTag", "isSuccess")
                    }
                    Resource.Status.ERROR -> {
                        Log.d("MainActivityTag", state.userList.message.toString())
                    }
                    Resource.Status.LOADING -> {
                        Log.d("MainActivityTag", "isLoading")
                    }
                }
            }
        }
    }
}
