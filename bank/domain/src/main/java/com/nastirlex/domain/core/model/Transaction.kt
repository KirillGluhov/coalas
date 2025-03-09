package com.nastirlex.domain.core.model

data class Transaction(
    val account_id: Int,
    val add_info: String,
    val amount: String,
    val created_at: String,
    val status: String,
    val success_datetime: String?,
    val type: String
)