package com.nastirlex.bank.presentation.transactions.navigation

import com.nastirlex.bank.presentation.common.navigation.Destination

object TransactionsDestination : Destination() {
    const val accountIdArg = "account_id"

    override fun args(): List<String> = listOf(accountIdArg)
}