package com.nastirlex.domain.core.model

data class Customer(
    val accounts_count: AccountsCount,
    val created_at: String,
    val id: Int,
    val is_banned: Int,
    val user_id: Int
)
