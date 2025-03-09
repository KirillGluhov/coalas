package com.nastirlex.data.net.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountsCount(
    val all: Int = 4,
    val credit: Int = 2,
    val debit: Int = 2,
)