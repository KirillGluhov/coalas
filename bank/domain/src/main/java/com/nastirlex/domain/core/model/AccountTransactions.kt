package com.nastirlex.domain.core.model

data class AccountTransactions(
    val balance: String,
    val close_date: Any?,
    val end_date: String,
    val id: Int,
    val open_date: String,
    val status: String,
    val transactions: List<Transaction>,
    val type: String
)