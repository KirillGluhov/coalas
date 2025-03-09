package com.nastirlex.bank.presentation.loanInfo

import androidx.lifecycle.viewModelScope
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.loanInfo.model.LoanInfoState
import com.nastirlex.domain.core.GetAccountsUseCase
import com.nastirlex.domain.core.ReplenishLoanUseCase
import com.nastirlex.domain.core.model.Transfer
import com.nastirlex.domain.core.model.TransferParam
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanInfoViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val replenishLoanUseCase: ReplenishLoanUseCase,
) : StatefulViewModel<LoanInfoState>() {

    init {
        getAccounts()
    }

    override fun createInitialState(): LoanInfoState = LoanInfoState()

    private fun getAccounts() = viewModelScope.launch {
        getAccountsUseCase(Unit).onSuccess { accounts ->
            updateState {
                copy(accounts = accounts)
            }
        }
    }

    fun onAccountIdSelected(accountId: String) {
        updateState {
            copy(selectedAccountId = accountId)
        }
    }

    fun onAccountForAutoDebtBottomSheetChangeVisibility(isVisible: Boolean) {
        updateState {
            copy(showSelectAccountBottomSheet = isVisible)
        }
    }

    fun turnOffAutoDebt() = viewModelScope.launch { }

    fun replenishLoan() = viewModelScope.launch {
        replenishLoanUseCase(
            TransferParam(
                loanId = currentScreenState.loan.id,
                transferBody = Transfer(money = currentScreenState.selectedAmount.takeIf { it.isNotBlank() }
                    ?.toInt() ?: 0, accountId = currentScreenState.selectedAccountId)
            )
        ).onSuccess { }
    }

}