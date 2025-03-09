package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Passport
import kotlinx.serialization.Serializable

@Serializable
data class PassportDto(
    val created_at: String,
    val id: Int,
    val issue_date: String,
    val issue_place: String,
    val number: Int,
    val series: Int,
    val unitcode: String,
    val updated_at: String,
    val user_id: Int
)

internal fun PassportDto.toDomain(): Passport =
    Passport(
        created_at, id, issue_date, issue_place, number, series, unitcode, updated_at, user_id
    )