package com.example.data.mapper

import com.example.data.dto.DummyDto
import com.example.data.local.entity.Fortunes

object FortuneMapper {
    fun dtoToEntity(dto: DummyDto.Fortune): Fortunes {
        return Fortunes(
            id = dto.id,
            fortune = dto.fortune
        )
    }


    fun entityToModel(fortunes: Fortunes): com.example.domain.model.Fortunes {
        return com.example.domain.model.Fortunes(
            fortuneNo = fortunes.id,
            fortune = fortunes.fortune
        )
    }
}