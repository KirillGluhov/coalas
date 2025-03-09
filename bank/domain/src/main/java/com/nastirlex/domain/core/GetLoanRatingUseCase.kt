package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.Rating
import javax.inject.Inject

interface GetLoanRatingUseCase : SuspendedUseCase<Int, Rating>

class GetLoanRatingUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : GetLoanRatingUseCase {
    override suspend fun execute(param: Int): Rating =
        coreDataSource.getLoanRating(param)
}