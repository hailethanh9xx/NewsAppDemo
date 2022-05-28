package com.aiden.newsdemo

import android.os.Bundle
import com.aiden.newsdemo.databinding.ActivityMainBinding
import com.aiden.newsdemo.presentation.base.BaseActivity
import com.aiden.newsdemo.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val binding by viewBinding { ActivityMainBinding.inflate(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}