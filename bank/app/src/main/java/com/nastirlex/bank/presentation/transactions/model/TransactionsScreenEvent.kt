package com.nastirlex.bank.presentation.transactions.model

import com.nastirlex.bank.presentation.core.Event

sealed class TransactionsScreenEvent : Event {
    data class OpenTransactionsScreen(val accountId: String) : TransactionsScreenEvent()
    data class OpenReplenishBottomSheet(val accountId: String) : TransactionsScreenEvent()
}