package com.nastirlex.domain.core.model

data class Rate(
    val count_loans: Int,
    val description: String,
    val end_date: String,
    val id: Int,
    val name: String,
    val rate: Double,
    val start_date: String,
    val status: String
)