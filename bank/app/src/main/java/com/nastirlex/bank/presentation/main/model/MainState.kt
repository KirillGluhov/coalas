package com.nastirlex.bank.presentation.main.model

import com.nastirlex.bank.presentation.core.Event
import com.nastirlex.bank.presentation.core.ScreenState
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.ShortLoan


data class MainState(
    val accounts: List<Account> = listOf(Account(), Account()),
    val loans: List<ShortLoan> = listOf(ShortLoan(), ShortLoan()),
    val selectedAccountId: String = "",
    val amountEditValue: String = "",
    val withdrawAmountEditValue: String = "",
    val showReplenishAccountBottomSheet: Boolean = false,
    val showWithdrawAccountBottomSheet: Boolean = false,
) : ScreenState

sealed interface MainScreenEvent : Event {
    data object OpenReplenishAccountBottomSheet : MainScreenEvent
    data object OnWithdrawAccountButtonClicked : MainScreenEvent
}
