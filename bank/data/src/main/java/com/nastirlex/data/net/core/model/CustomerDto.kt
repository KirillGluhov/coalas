package com.nastirlex.data.net.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomerDto(
    val accounts_count: AccountsCount = AccountsCount(),
    val created_at: String = "created_at",
    val id: Int = 1,
    val is_banned: Int = 0,
    val user_id: Int = 1,
)