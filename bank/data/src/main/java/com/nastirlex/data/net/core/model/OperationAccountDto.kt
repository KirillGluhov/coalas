package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.OperationAccount
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OperationAccountDto(
    @SerialName("money")
    val money: Int,
)

internal fun OperationAccountDto.toDomain(): OperationAccount =
    OperationAccount(
        money.toDouble() / 100,
    )

internal fun OperationAccount.toDto(): OperationAccountDto =
    OperationAccountDto((amount * 100).toInt())