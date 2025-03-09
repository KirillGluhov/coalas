package com.nastirlex.domain.core.model

data class ShortLoan(
    val id: String = "1",
    val tariff: ShortTariff = ShortTariff(),
    val size: Int = 12,
    val closeDate: String = "Default Loan close date",
    val procents: Double = 0.0,
    val debt: Int = 0,
    val isAutodebt: Boolean = false,
)
