package com.example.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.UserFortunes
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.initFortune()
        binding.btnClick.setOnClickListener {
            val userName = binding.edtName.text.toString().trim()
            viewModel.randomFortune().observeForever{fortunesList ->
                viewModel.insertUserFortunes(UserFortunes(userName, fortunesList[0].fortune))
            }
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("user_name",binding.edtName.text.toString())
            startActivity(intent)
        }


    }
}