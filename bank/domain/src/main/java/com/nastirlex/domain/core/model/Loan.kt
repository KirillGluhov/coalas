package com.nastirlex.domain.core.model

data class Loan(
    val id: String = "1",
    val tariff: ShortTariff = ShortTariff(),
    val accountId: String = "Default Loan account id",
    val size: Int = 12,
    val openDate: String = "Default Loan open date",
    val closeDate: String = "Default Loan close date",
    val procents: Double = 0.0,
    val debt: Int = 0,
    val payout: Int = 0,
    val isAutodebt: Boolean = false,
)
