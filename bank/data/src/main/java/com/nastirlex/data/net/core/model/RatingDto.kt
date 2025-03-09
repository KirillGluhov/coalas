package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.Rating
import kotlinx.serialization.Serializable

@Serializable
data class RatingDto(
    val rating: Int = 4
)

internal fun RatingDto.toDomain(): Rating =
    Rating(rating)