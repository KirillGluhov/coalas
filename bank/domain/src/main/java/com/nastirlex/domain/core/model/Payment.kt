package com.nastirlex.domain.core.model

data class Payment(
    val amount: Int,
    val date: String,
    val status: String,
    val transaction_id: Int
)