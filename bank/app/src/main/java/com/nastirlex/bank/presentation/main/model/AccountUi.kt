package com.nastirlex.bank.presentation.main.model

data class AccountUi(
    val balance: String,
    val close_date: String?,
    val end_date: String,
    val id: Int,
    val open_date: String,
    val status: String,
    val currency: String,
    val type: String,
    var isHidden: Boolean = false
)
