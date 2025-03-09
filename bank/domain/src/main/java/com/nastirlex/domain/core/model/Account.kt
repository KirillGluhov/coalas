package com.nastirlex.domain.core.model

data class Account(
    val id: String = "1",
    val openDate: String = "Default Account open date",
    val closeDate: String = "Default Account close date",
    val status: String = "Default Account status",
    val balance: Double = 345.566,
    var isHidden: Boolean = false,
)