package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserFortunes
import com.example.domain.usecase.FetchFortunesUseCase
import com.example.domain.usecase.GetUserFortunesDuplicateUsecase
import com.example.domain.usecase.GetUserFortunesUseCase
import com.example.domain.usecase.InsertUserFortunesUseCase
import com.example.domain.usecase.RandomFortuneUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchFortunesUseCase: FetchFortunesUseCase,
    private val randomFortuneUseCase: RandomFortuneUseCase,
    private val insertUserFortunesUseCase: InsertUserFortunesUseCase,
    private val getUserFortunesUseCase: GetUserFortunesUseCase,
    private val getUserFortunesDuplicateUsecase: GetUserFortunesDuplicateUsecase

) : ViewModel() {


    private val _fortune = MutableLiveData<List<UserFortunes>>()
    val fortune: LiveData<List<UserFortunes>> get() = _fortune

    private val _isDataLoaded = MutableLiveData<Boolean>()
    val isDataLoaded: LiveData<Boolean> get() = _isDataLoaded

    private val _isDuplicated = MutableLiveData<Boolean>()
    val isDuplicated: LiveData<Boolean> get() = _isDuplicated

    // 운세 Room DB에 저장
    fun fetchFortunes() {
        viewModelScope.launch {
            fetchFortunesUseCase()
        }
    }

    // 중복 확인 후 UserFortunes 삽입
    fun insertUserFortunes(userName: String, createdDate: String) {
        viewModelScope.launch {
            val fortune = randomFortuneUseCase() // suspend 함수 호출
            val userFortunes = UserFortunes(userName, fortune.fortune, createdDate)

            val fortuneList = getUserFortunesDuplicateUsecase(userName)
            if (fortuneList.isEmpty()) {
                insertUserFortunesUseCase(userFortunes)
                _isDuplicated.postValue(true)
            } else {
                _isDuplicated.postValue(false)
            }
        }
    }

    // 중복 확인 후 데이터 삽입 과정이 있었으니 확실히 데이터가 있다, 고로 있는 데이터 불러오기
    fun getUserFortunes(name: String) {
        viewModelScope.launch {
            val userFortuneList = getUserFortunesUseCase(name)
            _fortune.postValue(userFortuneList)
            _isDataLoaded.postValue(true)  // 🔹 데이터 로딩 완료 표시

        }

    }
}