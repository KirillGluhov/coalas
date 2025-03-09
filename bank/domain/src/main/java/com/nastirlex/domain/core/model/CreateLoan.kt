package com.nastirlex.domain.core.model

data class CreateLoan(
    val tariffId: String = "Default CreateLoanDto tariff id",
    /**
     * счет, куда поступят деньги, если не передаем - создается новый
     */
    val accountId: String? = null,
    val size: Int = 0,
    val closeDate: String
)
