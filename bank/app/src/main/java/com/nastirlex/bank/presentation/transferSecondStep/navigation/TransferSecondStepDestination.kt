package com.nastirlex.bank.presentation.transferSecondStep.navigation

import com.nastirlex.bank.presentation.common.navigation.Destination

object TransferSecondStepDestination : Destination() {
    const val recipientId = "recipient_id"

    override fun args(): List<String> = listOf(recipientId)
}