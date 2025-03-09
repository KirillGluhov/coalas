package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Account
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    @SerialName("id")
    val id: String = "1",
    @SerialName("open_date")
    val openDate: String = "",
    @SerialName("close_date")
    val closeDate: String = "",
    @SerialName("status")
    val status: Status = Status.OPEN,
    @SerialName("balance")
    val balance: Int = 9980,
)

@Serializable
enum class Status {
    @SerialName("open")
    OPEN,
    @SerialName("close")
    CLOSE,
}

fun AccountDto.toDomain(): Account =
    Account(
        id, openDate, closeDate, status.name, balance.toDouble() / 100.0,
    )