package com.nastirlex.bank.presentation.transferFirstStep

import androidx.lifecycle.viewModelScope
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.transferFirstStep.model.TransferFirstStepState
import com.nastirlex.domain.core.GetAccountsUseCase
import com.nastirlex.domain.core.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferFirstStepViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
) :
    StatefulViewModel<TransferFirstStepState>() {

    init {
        getContacts()
    }

    override fun createInitialState(): TransferFirstStepState =
        TransferFirstStepState(
            accounts = emptyList()
        )

    private fun getContacts() = viewModelScope.launch {
        val contacts = mutableListOf<Account>()
        getAccountsUseCase(Unit)
            .onSuccess {
                contacts.add(it[0])
                updateState {
                    copy(accounts = contacts)
                }
            }
        getAccountsUseCase(Unit)
            .onSuccess {
                contacts.add(it[0])
                updateState {
                    copy(accounts = contacts)
                }
            }
    }
}