package com.nastirlex.bank.presentation.newAccounts.model

import com.nastirlex.bank.presentation.core.ScreenState
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.Tariff

data class NewAccountsState(
    val tariffs: List<Tariff> = listOf(Tariff(), Tariff()),
    val rating: String = "3",
    val selectedAccountId: String = "",
    val accountIdExpanded: Boolean = false,
    val accounts: List<Account> = listOf(Account(), Account()),
    val loanSize: String = "",
    val selectedTariffId: String = "",
    val showCreateLoanBottomSheet: Boolean = false,
) : ScreenState