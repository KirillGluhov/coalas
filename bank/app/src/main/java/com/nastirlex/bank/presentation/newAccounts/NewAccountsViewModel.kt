package com.nastirlex.bank.presentation.newAccounts

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.newAccounts.model.NewAccountsState
import com.nastirlex.domain.core.CreateLoanUseCase
import com.nastirlex.domain.core.GetAccountsUseCase
import com.nastirlex.domain.core.GetLoanRatingUseCase
import com.nastirlex.domain.core.GetTariffsUseCase
import com.nastirlex.domain.core.model.CreateLoan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewAccountsViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val getTariffsUseCase: GetTariffsUseCase,
    private val getLoanRatingUseCase: GetLoanRatingUseCase,
    private val createLoanUseCase: CreateLoanUseCase,
) : StatefulViewModel<NewAccountsState>() {

    init {
        getAccounts()
        getTariffs()
    }

    override fun createInitialState(): NewAccountsState =
        NewAccountsState()


    private fun getCreditRating() = viewModelScope.launch {
        getLoanRatingUseCase(1).onSuccess {
            updateState {
                copy(
                    rating = it.rating.toString(),
                )
            }
        }
    }

    private fun getAccounts() = viewModelScope.launch {
        getAccountsUseCase(Unit).onSuccess { accounts ->
            updateState {
                copy(accounts = accounts)
            }
        }
    }

    private fun getTariffs() = viewModelScope.launch {
        getTariffsUseCase(Unit).onSuccess { tariffs ->
            updateState {
                copy(
                    tariffs = tariffs,
                )
            }
        }.onFailure {
            Log.d("error", it.localizedMessage ?: "")
        }
    }

    fun onTariffClick(tariffId: String) {
        updateState {
            copy(selectedTariffId = tariffId, showCreateLoanBottomSheet = true)
        }
    }

    fun onLoanSizeChanged(newSize: String) {
        updateState {
            copy(loanSize = newSize)
        }
    }

    fun onAccountIdSelected(accountId: String) {
        updateState {
            copy(selectedAccountId = accountId)
        }
    }

    fun createLoan() = viewModelScope.launch {
        createLoanUseCase(
            CreateLoan(
                tariffId = currentScreenState.selectedTariffId,
                accountId = currentScreenState.selectedAccountId,
                size = currentScreenState.loanSize.takeIf { it.isNotBlank() }?.toInt() ?: 0,
                closeDate = "",
            )
        )
    }

    fun onBottomSheetChangeVisibility(isVisible: Boolean) {
        updateState {
            copy(showCreateLoanBottomSheet = isVisible)
        }
    }

    fun onCreditClick() = viewModelScope.launch {}
}