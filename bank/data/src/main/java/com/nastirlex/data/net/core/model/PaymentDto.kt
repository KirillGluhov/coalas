package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Payment
import kotlinx.serialization.Serializable

@Serializable
data class PaymentDto(
    val amount: Int,
    val date: String,
    val status: String,
    val transaction_id: Int
)

internal fun PaymentDto.toDomain(): Payment =
    Payment(amount, date, status, transaction_id)