package com.example.data.mapper

import com.example.data.dto.DummyDto
import com.example.domain.model.Fortunes

object FortuneMapper {
    // DummyDto.Fortune 리스트를 Domain 모델인 Fortunes 리스트로 변환하는 메서드
    fun entityToDomainMapper(dtoList: List<DummyDto.Fortune>): List<Fortunes> {
        return dtoList.map { dto ->
            Fortunes(
                fortuneNo = dto.id,
                fortune = dto.fortune
            )
        }
    }
}