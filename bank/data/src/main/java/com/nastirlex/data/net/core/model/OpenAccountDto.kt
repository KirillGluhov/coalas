package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.OpenAccount
import kotlinx.serialization.Serializable

@Serializable
data class OpenAccountDto(
    val customer_id: String,
    val type: String,
    val currency: String,
)

internal fun OpenAccount.toDto(): OpenAccountDto =
    OpenAccountDto(customerId, type, currency)
