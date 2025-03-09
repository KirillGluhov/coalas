package com.nastirlex.bank.presentation.transactions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.transactions.model.TransactionsState
import com.nastirlex.bank.presentation.transactions.navigation.TransactionsDestination
import com.nastirlex.domain.core.GetTransactionsUseCase
import com.nastirlex.domain.core.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    savedStateHandle: SavedStateHandle,
) : StatefulViewModel<TransactionsState>() {

    private var accountId: Int =
        checkNotNull(savedStateHandle[TransactionsDestination.accountIdArg])

    init {
        accountId = checkNotNull(savedStateHandle[TransactionsDestination.accountIdArg])
//        getTransactions(accountId)
    }

    override fun createInitialState(): TransactionsState =
        TransactionsState()

    private val _socketStatus = MutableLiveData(false)
    val socketStatus: LiveData<Boolean> = _socketStatus

    private val _messages = MutableLiveData<Pair<Boolean, String>>()
    val messages: LiveData<Pair<Boolean, String>> = _messages

    fun addMessage(message: Pair<Boolean, String>) = viewModelScope.launch(Dispatchers.Main) {
        if (_socketStatus.value == true) {
            _messages.value = message
        }
    }

    fun setStatus(status: Boolean) = viewModelScope.launch(Dispatchers.Main) {
        _socketStatus.value = status
    }

    private fun getTransactions(accountId: Int) = viewModelScope.launch {
        getTransactionsUseCase(accountId).onSuccess {
            lateinit var sortedKeys: List<String>
            val groupedTransactionsMap = mutableMapOf<String, List<Transaction>>()
            val groupedTransactions = it.transactions.filter { it.status == "success" }
                .groupBy { it.success_datetime?.split(' ')?.get(0) ?: "" }.also {
                    val keyList = it.keys.toList()
                    sortedKeys = keyList.sortedDescending()
                }
            sortedKeys.forEach { key ->
                val g = groupedTransactions[key]
                groupedTransactionsMap[key] = groupedTransactions[key] ?: emptyList()
            }
            updateState {
                copy(
                    transactions = groupedTransactionsMap.toMap(),
                )
            }

        }.onFailure {
            Log.d("error", it.localizedMessage ?: "")
        }
    }
}