package com.nastirlex.bank.presentation.transferSecondStep

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.transferSecondStep.model.TransferSecondStepScreenEvent
import com.nastirlex.bank.presentation.transferSecondStep.model.TransferSecondStepState
import com.nastirlex.bank.presentation.transferSecondStep.navigation.TransferSecondStepDestination
import com.nastirlex.domain.core.GetAccountsUseCase
import com.nastirlex.domain.core.TransferUseCase
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.Transfer
import com.nastirlex.domain.core.model.TransferParam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferSecondStepViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val transferUseCase: TransferUseCase,
    savedStateHandle: SavedStateHandle
) : StatefulViewModel<TransferSecondStepState>() {
    init {
        getCustomerAccounts()
    }
    private var recipientId: Int = checkNotNull(savedStateHandle[TransferSecondStepDestination.recipientId])

    init {
        getRecipientAccounts()
    }

    override fun createInitialState(): TransferSecondStepState =
        TransferSecondStepState(
            userAccounts = emptyList(),
            recipientAccounts = emptyList(),
            writeOffAccount = null,
            writeOffValue = "Счет списания",
            receiptAccount = null,
            receiptValue = "Счет получения",
            amount = "100",
        )

    private fun getRecipientAccounts() = viewModelScope.launch {
        getAccountsUseCase(Unit).onSuccess {
            updateState {
                copy(
//                    recipientAccounts = it.accounts.filter { it.status == "opened" }
                )
            }
        }
    }

    private fun onWriteOffValueChange(account: String) {
        updateState {
            copy(
                writeOffValue = account
            )
        }
    }

    private fun getCustomerAccounts() = viewModelScope.launch {
        getAccountsUseCase(Unit).onSuccess {
            updateState {
                copy(
//                    userAccounts = it.accounts.filter { it.status == "opened" },
                )
            }
        }
    }

    fun onAmountChange(amount: String) {
        updateState {
            copy(
                amount = amount,
            )
        }
    }

    fun onUserAccountClick(account: Account) {
        onWriteOffValueChange(account.balance.toString() + " " + "account.currency")
        updateState {
            copy(
                writeOffAccount = account,
            )
        }
    }

    private fun onRecipientValueChange(account: String) {
        updateState {
            copy(
                receiptValue = account
            )
        }
    }

    fun onRecipientAccountClick(account: Account) {
        onRecipientValueChange(account.balance.toString() + " " + "account.currency")
        updateState {
            copy(
                receiptAccount = account,
            )
        }
    }

    fun onTransferButtonClick() = viewModelScope.launch {
        if (currentScreenState.receiptAccount == null) {
            offerEvent(TransferSecondStepScreenEvent.ShowError("Не выбран счет получателя"))
            return@launch
        }
        transferUseCase(
            TransferParam(
                "1",
                Transfer(
                    money = currentScreenState.amount.toInt(),
                    accountId = currentScreenState.receiptAccount!!.id
                )
            )
        ).onSuccess {
            offerEvent(TransferSecondStepScreenEvent.OpenMainScreen)
        }.onFailure {
            offerEvent(TransferSecondStepScreenEvent.ShowError(it.localizedMessage ?: ""))
        }
    }
}