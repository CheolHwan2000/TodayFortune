package com.example.domain.usecase

import androidx.lifecycle.LiveData
import com.example.domain.model.UserFortunes
import com.example.domain.repository.UserFortunesRepository
import javax.inject.Inject

class GetUserFortunesDuplicateUsecase @Inject constructor(val repository: UserFortunesRepository){
    operator fun invoke(name: String): LiveData<List<UserFortunes>> {
        return repository.getUserFortuneDuplicate(name)
    }
}