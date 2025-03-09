package com.nastirlex.bank.presentation.transferSecondStep.model

import com.nastirlex.bank.presentation.core.Event

sealed class TransferSecondStepScreenEvent : Event {
    data object OpenMainScreen : TransferSecondStepScreenEvent()
    data class ShowError(val error: String): TransferSecondStepScreenEvent()
}