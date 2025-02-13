package com.example.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchFortunes()
        observe()
        binding.btnClick.setOnClickListener {
            val userName = binding.edtName.text.toString().trim()
            val createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            try {
                viewModel.insertUserFortunes(userName, createdDate)
            } catch (e: Exception) {
                Log.e("MainActivity_Exception", "${e.message}")
            }
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("user_name", binding.edtName.text.toString())
            startActivity(intent)
        }
    }

    fun observe() {
        viewModel.isDuplicated.observe(this) {
            if (it) {
                Toast.makeText(this, "데이터 등록 성공", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity_true", "$it")
            } else {
                Toast.makeText(this, "데이터 중복", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity_false", "$it")
            }
        }

    }
}