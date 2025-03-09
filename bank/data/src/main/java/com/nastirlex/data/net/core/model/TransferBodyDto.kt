package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Transfer
import com.nastirlex.domain.core.model.TransferParam
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransferBodyDto(
    @SerialName("money")
    val money: Int,
    @SerialName("account_id")
    val accountId: String,
)

internal fun Transfer.toDto(): TransferBodyDto =
    TransferBodyDto(
        money, accountId,
    )
