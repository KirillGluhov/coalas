package com.nastirlex.bank.presentation.transactions.model

import com.nastirlex.bank.presentation.core.ScreenState
import com.nastirlex.data.net.core.model.TransactionDto
import com.nastirlex.data.net.core.model.toDomain
import com.nastirlex.domain.core.model.Transaction

data class TransactionsState(
    val transactions: Map<String, List<Transaction>> = mapOf(
        pair = Pair(
            "date",
            listOf(TransactionDto().toDomain(), TransactionDto().toDomain(), TransactionDto().toDomain(), TransactionDto().toDomain())
        )
    )
) : ScreenState