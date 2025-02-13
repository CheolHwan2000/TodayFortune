package com.example.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityLengthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LengthActivity : AppCompatActivity() {
    lateinit var binding: ActivityLengthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLengthBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val length = intent.getIntExtra("length", 0)
        binding.tvLength.text = length.toString()

    }
}