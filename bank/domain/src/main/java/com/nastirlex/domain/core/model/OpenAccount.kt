package com.nastirlex.domain.core.model

data class OpenAccount(
    val customerId: String,
    val type: String = "debit",
    val currency: String = "rub",
)
