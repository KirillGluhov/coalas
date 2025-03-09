package com.nastirlex.bank.presentation.loanInfo.model

import com.nastirlex.bank.presentation.core.ScreenState
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.Loan

data class LoanInfoState(
    val loan: Loan = Loan(),
    val selectedAccountId: String = "",
    val accounts: List<Account> = listOf(Account()),
    val showSelectAccountBottomSheet: Boolean = false,
    val selectedAmount: String = "",
    val showAmountForReplenishBottomSheet: Boolean = false,
) : ScreenState
