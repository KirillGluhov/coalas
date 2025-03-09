package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Loan
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoanDto(
    @SerialName("id")
    val id: String = "Default LoanDto id",
    @SerialName("tariff")
    val tariff: ShortTariffDto = ShortTariffDto(),
    @SerialName("account_id")
    val accountId: String = "Default LoanDto account id",
    @SerialName("size")
    val size: Int = 0,
    @SerialName("open_date")
    val openDate: String = "Default LoanDto open date",
    @SerialName("close_date")
    val closeDate: String = "Default LoanDto close date",
    @SerialName("procents")
    val procents: Double = 0.0,
    @SerialName("debt")
    val debt: Int = 0,
    /**
     * сумма выплат
     */
    @SerialName("payout")
    val payout: Int = 0,
    /**
     * включен ли автоплатеж
     */
    @SerialName("is_autodebt")
    val isAutodebt: Boolean = false,
)

fun LoanDto.toDomain(): Loan = Loan(
    id, tariff.toDomain(), accountId, size, openDate, closeDate, procents, debt, payout, isAutodebt
)