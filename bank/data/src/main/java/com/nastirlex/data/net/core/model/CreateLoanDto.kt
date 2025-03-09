package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.CreateLoan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateLoanDto(
    @SerialName("tariff_id")
    val tariffId: String = "Default CreateLoanDto tariff id",
    /**
     * счет, куда поступят деньги, если не передаем - создается новый
     */
    @SerialName("account_id")
    val accountId: String? = null,
    @SerialName("size")
    val size: Int = 0,
    @SerialName("close_date")
    val closeDate: String
)

fun CreateLoan.toDto() = CreateLoanDto(
    tariffId, accountId, size, closeDate
)