package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.CreateLoan
import javax.inject.Inject

interface CreateLoanUseCase : SuspendedUseCase<CreateLoan, Unit>

class CreateLoanUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : CreateLoanUseCase {
    override suspend fun execute(param: CreateLoan) =
        coreDataSource.createLoan(param)
}