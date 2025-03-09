package com.nastirlex.bank.presentation.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.main.model.MainState
import com.nastirlex.bank.presentation.transactions.model.TransactionsScreenEvent
import com.nastirlex.domain.core.CloseAccountUseCase
import com.nastirlex.domain.core.WithdrawAccountUseCase
import com.nastirlex.domain.core.GetAccountsUseCase
import com.nastirlex.domain.core.GetLoansUseCaseImpl
import com.nastirlex.domain.core.OpenAccountUseCase
import com.nastirlex.domain.core.ReplenishAccountUseCase
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.OpenAccount
import com.nastirlex.domain.core.model.OperationAccount
import com.nastirlex.domain.core.model.OperationAccountParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getLoansUseCase: GetLoansUseCaseImpl,
    private val replenishAccountUseCase: ReplenishAccountUseCase,
    private val withdrawAccountUseCase: WithdrawAccountUseCase,
    private val openAccountUseCase: OpenAccountUseCase,
    private val closeAccountUseCase: CloseAccountUseCase,
) : StatefulViewModel<MainState>() {

    init {
        getAccounts()
        getLoans()
    }

    private var id: Int = 0

    override fun createInitialState(): MainState = MainState()

    fun updateReplenishAccountBottomSheetVisibility(value: Boolean) = updateState {
        copy(showReplenishAccountBottomSheet = value)
    }

    fun updateWithdrawAccountBottomSheetVisibility(value: Boolean) = updateState {
        copy(showWithdrawAccountBottomSheet = value)
    }

    fun onAmountValueChange(newValue: String) = updateState {
        copy(amountEditValue = newValue)
    }

    private fun getAccounts() {
        viewModelScope.launch(Dispatchers.IO) {
            getAccountsUseCase(Unit).onSuccess { accounts ->
                updateState {
                    copy(
                        accounts = accounts,
                    )
                }
            }.onFailure {
                Log.d("error", it.localizedMessage ?: "")
            }
        }
    }

    private fun getLoans() =
        viewModelScope.launch(Dispatchers.IO) {
            getLoansUseCase(Unit)
                .onSuccess { loans ->
                    updateState {
                        copy(
                            loans = loans,
                        )
                    }
                }
        }


    fun openReplenishAccountBottomSheet(accountId: String) = viewModelScope.launch {
        updateState {
            copy(showReplenishAccountBottomSheet = true, selectedAccountId = accountId)
        }
    }

    fun replenishAccount() = viewModelScope.launch {
        updateReplenishAccountBottomSheetVisibility(false)

        updateState {
            copy(amountEditValue = "")
        }

        replenishAccountUseCase(
            OperationAccountParam(
                currentScreenState.selectedAccountId,
                OperationAccount(
                    amount = currentScreenState.amountEditValue.toDouble()
                )
            )
        ).onSuccess {
            getAccounts()
        }.onFailure {
            Log.d("error", it.localizedMessage ?: "")
        }
    }

    fun openWithdrawAccountBottomSheet(accountId: String) = viewModelScope.launch {
        updateState {
            copy(showWithdrawAccountBottomSheet = true, selectedAccountId = accountId)
        }

    }

    fun withdrawAccount() = viewModelScope.launch {
        updateWithdrawAccountBottomSheetVisibility(false)

        updateState {
            copy(amountEditValue = "")
        }

        withdrawAccountUseCase(
            OperationAccountParam(
                currentScreenState.selectedAccountId,
                OperationAccount(
                    amount = currentScreenState.amountEditValue.toDouble()
                )
            )
        ).onSuccess {
                getAccounts()
            }.onFailure {
                Log.d("error", it.localizedMessage ?: "")
            }
    }

    fun openAccount() = viewModelScope.launch {
        openAccountUseCase(OpenAccount(customerId = id.toString()))
            .onSuccess {
                getAccounts()
            }.onFailure {
                Log.d("error", it.localizedMessage ?: "")
            }
    }

    fun closeAccount(accountId: String) = viewModelScope.launch {
        closeAccountUseCase(accountId).onSuccess {
            getAccounts()
        }.onFailure {
            Log.d("error", it.localizedMessage ?: "")
        }
    }

    fun onAccountClick(accountId: String) {
        offerEvent(TransactionsScreenEvent.OpenTransactionsScreen(accountId))
    }

    fun onAccountVisibilityChange(index: Int, isCredit: Boolean, account: Account) {
        val newAccounts = currentScreenState.accounts.toMutableList()
        newAccounts[index] = account.copy(isHidden = !account.isHidden)

        updateState {
            copy(
                accounts = newAccounts,
            )
        }
    }
}