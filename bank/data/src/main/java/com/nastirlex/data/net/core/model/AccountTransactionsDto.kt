package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.AccountTransactions
import kotlinx.serialization.Serializable

@Serializable
data class AccountTransactionsDto(
    val balance: String = "8888",
    val close_date: String? = null,
    val end_date: String = "transaction end date",
    val id: Int = 1,
    val open_date: String = "transaction open date",
    val status: String = "transaction status",
    val transactions: List<TransactionDto> = listOf(TransactionDto(), TransactionDto(), TransactionDto()),
    val type: String = "AccountTransaction type",
)

internal fun AccountTransactionsDto.toDomain(): AccountTransactions =
    AccountTransactions(
        balance,
        close_date,
        end_date,
        id,
        open_date,
        status,
        transactions.map { it.toDomain() },
        type,
    )