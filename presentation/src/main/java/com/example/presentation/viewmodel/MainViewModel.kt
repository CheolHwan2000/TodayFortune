package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dto.DummyDto
import com.example.data.mapper.FortuneMapper
import com.example.domain.model.Fortunes
import com.example.domain.model.UserFortunes
import com.example.domain.usecase.GetUserFortunesUseCase
import com.example.domain.usecase.InitFortunesUseCase
import com.example.domain.usecase.InsertUserFortunesUseCase
import com.example.domain.usecase.RandomFortuneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val initFortunesUseCase: InitFortunesUseCase,
    private val randomFortuneUseCase: RandomFortuneUseCase,
    private val insertUserFortunesUseCase : InsertUserFortunesUseCase,
    private val getUserFortunesUseCase: GetUserFortunesUseCase
) : ViewModel() {


    private val _fortune = MutableLiveData<List<UserFortunes>>()
    val fortune: LiveData<List<UserFortunes>> get() = _fortune

    private val _isDataLoaded = MutableLiveData<Boolean>()
    val isDataLoaded: LiveData<Boolean> get() = _isDataLoaded




    fun initFortune() {
        // DummyDto.fortuneData 리스트 전체를 Fortunes 리스트로 변환
        val fortunesList = FortuneMapper.entityToDomainMapper(DummyDto.fortuneData)

        // 변환된 리스트를 DB에 저장하는 작업
        CoroutineScope(Dispatchers.IO).launch {
            fortunesList.forEach { fortune ->
                initFortunesUseCase.invoke(fortune)
            }
        }
    }

    fun randomFortune() : LiveData<List<Fortunes>>{
        return randomFortuneUseCase.invoke()
    }

    fun insertUserFortunes(userFortunes: UserFortunes){
        CoroutineScope(Dispatchers.IO).launch {
            insertUserFortunesUseCase.invoke(userFortunes)
        }
    }

    fun getUserFortunes(name : String){
        viewModelScope.launch {
            getUserFortunesUseCase.invoke(name).observeForever{fortunes ->
                _fortune.postValue(fortunes ?: emptyList())
                _isDataLoaded.postValue(true)  // 🔹 데이터 로딩 완료 표시

            }
        }
    }



}