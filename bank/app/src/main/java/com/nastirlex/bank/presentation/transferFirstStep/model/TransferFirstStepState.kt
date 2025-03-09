package com.nastirlex.bank.presentation.transferFirstStep.model

import com.nastirlex.bank.presentation.core.ScreenState
import com.nastirlex.domain.core.model.Account

data class TransferFirstStepState(
    val accounts: List<Account>,
    val contacts: List<Contact> = listOf(
        Contact(2, "Maria Stepanova", "+ 7 924 344-34-44"),
        Contact(3, "Lidia Hill", "+ 7 953 643-23-11")
    ),
) : ScreenState

data class Contact(
    val id: Int,
    val name: String,
    val phone: String,
)