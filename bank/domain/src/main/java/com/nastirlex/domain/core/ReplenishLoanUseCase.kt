package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.TransferParam
import javax.inject.Inject

interface ReplenishLoanUseCase : SuspendedUseCase<TransferParam, Unit>

class ReplenishLoanUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : ReplenishLoanUseCase {
    override suspend fun execute(param: TransferParam) =
        coreDataSource.replenishLoan(param.loanId, param.transferBody)
}