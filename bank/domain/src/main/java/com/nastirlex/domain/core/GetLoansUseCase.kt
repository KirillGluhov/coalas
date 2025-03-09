package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.ShortLoan
import javax.inject.Inject

interface GetLoansUseCase : SuspendedUseCase<Unit, List<ShortLoan>>

class GetLoansUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : GetLoansUseCase {
    override suspend fun execute(param: Unit): List<ShortLoan> =
        coreDataSource.getLoans()

}