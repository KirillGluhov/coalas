package com.nastirlex.bank.presentation.transferSecondStep.model

import com.nastirlex.bank.presentation.core.ScreenState
import com.nastirlex.domain.core.model.Account

data class TransferSecondStepState(
    val userAccounts: List<Account>,
    val recipientAccounts: List<Account>,
    val writeOffAccount: Account?,
    val writeOffValue: String,
    val receiptAccount: Account?,
    val receiptValue: String,
    val amount: String,
) : ScreenState