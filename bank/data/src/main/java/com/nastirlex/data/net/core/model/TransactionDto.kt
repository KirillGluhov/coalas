package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Transaction
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDto(
    val account_id: Int = 1,
    val add_info: String = "",
    val amount: String = "123456",
    val created_at: String = "2023-04-01T12:34:56Z",
    val status: String = "transaction status",
    val success_datetime: String? = null,
    val type: String = "transaction type",
)

fun TransactionDto.toDomain(): Transaction =
    Transaction(
        account_id, add_info, amount, created_at, status, success_datetime, type
    )