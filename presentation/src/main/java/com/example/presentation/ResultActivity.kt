package com.example.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityResultBinding
import com.example.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    lateinit var binding : ActivityResultBinding
    private val viewModel : MainViewModel by viewModels()
    var textLength = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userName = intent.getStringExtra("user_name")

        viewModel.getUserFortunes(userName!!)
        observe()

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, LengthActivity::class.java)
            intent.putExtra("length",textLength)
            startActivity(intent)
        }



    }
    fun observe() {
        viewModel.isDataLoaded.observe(this) { isLoaded ->
            if (isLoaded) {  // 데이터 로딩 완료 후 옵저버 등록
                viewModel.fortune.observe(this) { userFortunes ->
                    if (userFortunes.isNotEmpty()) {
                        binding.tvName.text = userFortunes[0].name
                        binding.tvFortune.text = userFortunes[0].todayFortune
                        textLength = userFortunes[0].todayFortune.length

                    }else{
                        binding.tvName.text = "로딩중"
                        binding.tvFortune.text = "로딩중"
                    }
                }
            }
        }
    }

}