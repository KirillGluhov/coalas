package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Loan
import com.nastirlex.domain.core.model.ShortLoan
import com.nastirlex.domain.core.model.ShortTariff
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShortLoanDto(
    @SerialName("id")
    val id: String = "1",
    @SerialName("tariff")
    val tariff: ShortTariffDto = ShortTariffDto(),
    @SerialName("size")
    val size: Int = 0,
    @SerialName("close_date")
    val closeDate: String = "",
    @SerialName("procents")
    val procents: Double = 0.0,
    @SerialName("debt")
    val debt: Int = 0,
    /**
    * включен ли автоплатеж
    */
    @SerialName("is_autodebt")
    val isAutodebt: Boolean = false,
)

@Serializable
data class ShortTariffDto(
    @SerialName("name")
    val name: String = "Deafult ShortTariffDto name",
    @SerialName("procent")
    val procent: Double = 0.0,
)

fun ShortTariffDto.toDomain(): ShortTariff = ShortTariff(
    name, procent
)

fun ShortLoanDto.toDomain(): ShortLoan =
    ShortLoan(
        id, tariff.toDomain(), size, closeDate, procents, debt, isAutodebt
    )