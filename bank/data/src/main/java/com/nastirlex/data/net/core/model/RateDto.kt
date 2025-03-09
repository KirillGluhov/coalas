package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Rate
import kotlinx.serialization.Serializable

@Serializable
data class RateDto(
    val count_loans: Int = 5,
    val description: String = "rate description",
    val end_date: String = "rate end_date",
    val id: Int = 1,
    val name: String = "rate name",
    val rate: Double = 5.0,
    val start_date: String = "rate start date",
    val status: String = "rate status",
)

fun RateDto.toDomain(): Rate =
    Rate(count_loans, description, end_date, id, name, rate, start_date, status)