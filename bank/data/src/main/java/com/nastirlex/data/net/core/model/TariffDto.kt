package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Tariff
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TariffDto(
    @SerialName("id")
    val id: String = "Default TariffDto id",
    @SerialName("name")
    val name: String = "Default TariffDto name",
    @SerialName("procents")
    val procents: String = "Default TariffDto procents",
)

internal fun TariffDto.toDomain(): Tariff =
    Tariff(id, name, procents)