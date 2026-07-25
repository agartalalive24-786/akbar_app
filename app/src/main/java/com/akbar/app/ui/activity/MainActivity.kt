package com.akbar.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akbar.app.databinding.ActivityMainBinding
import com.akbar.app.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        binding.welcomeText.text = "Welcome to Akbar App"
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this) { state ->
            // Handle UI state changes
        }
    }
}
