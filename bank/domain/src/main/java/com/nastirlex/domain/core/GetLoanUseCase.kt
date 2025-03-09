package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.Loan
import javax.inject.Inject

interface GetLoanUseCase: SuspendedUseCase<String, Loan>

class GetLoanUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : GetLoanUseCase {
    override suspend fun execute(param: String) =
        coreDataSource.getLoan(param)
}