package com.example.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Fortunes
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
    private val insertUserFortunesUseCase : InsertUserFortunesUseCase,
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
    fun fetchFortunes(){
        viewModelScope.launch {
            fetchFortunesUseCase()
        }
    }

    // Room DB에 저장된 운세 랜덤으로 가져오기
    fun randomFortune() : LiveData<List<Fortunes>>{
        return randomFortuneUseCase()
    }


    // 중복 확인 후 UserFortunes 삽입
    fun insertUserFortunes(userFortunes: UserFortunes){
        viewModelScope.launch {
            getUserFortunesDuplicateUsecase(userFortunes.name).observeForever{fortune ->
                if(fortune.isEmpty()){
                    viewModelScope.launch {
                        insertUserFortunesUseCase(userFortunes)
                    }
                    _isDuplicated.postValue(true)
                }else{
                    _isDuplicated.postValue(false)
                }
                Log.e("MainViewModel","$fortune")
            }
        }
    }

//    fun insertUserFortunes(userFortunes: UserFortunes) {
//        viewModelScope.launch {
//            // 중복 확인
//            val fortuneList = getUserFortunesDuplicateUsecase(userFortunes.name).value
//            if (fortuneList.isNullOrEmpty()) {
//                // 중복되지 않으면 삽입
//                insertUserFortunesUseCase(userFortunes)
//                _isDuplicated.postValue(true)  // 삽입 후 중복 처리 완료
//                Log.e("MainViewModel_Success","${getUserFortunesDuplicateUsecase(userFortunes.name).value}")
//
//            } else {
//                // 중복된 데이터가 있으면 실패 처리
//                _isDuplicated.postValue(false)
//                Log.e("MainViewModel_Fail","${getUserFortunesDuplicateUsecase(userFortunes.name).value}")
//
//            }
//        }
//    }

    // 중복 확인 후 데이터 불러오기
    fun getUserFortunes(name : String){
        viewModelScope.launch {
            getUserFortunesDuplicateUsecase(name).observeForever{fortunes ->
                if(fortunes.isEmpty()){
                    getUserFortunesUseCase(name).observeForever{fortune ->
                        _fortune.postValue(fortune ?: emptyList())
                        _isDataLoaded.postValue(true)  // 🔹 데이터 로딩 완료 표시

                    }
                }else{
                    _fortune.postValue(fortunes ?: emptyList())
                    _isDataLoaded.postValue(true)  // 🔹 데이터 로딩 완료 표시
                }
            }
        }

    }


}