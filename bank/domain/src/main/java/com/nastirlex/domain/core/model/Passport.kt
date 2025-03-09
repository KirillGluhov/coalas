package com.nastirlex.domain.core.model

data class Passport(
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