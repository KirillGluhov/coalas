package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.TransferParam
import javax.inject.Inject

interface TransferUseCase : SuspendedUseCase<TransferParam, Unit>

class TransferUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : TransferUseCase {
    override suspend fun execute(param: TransferParam) =
        coreDataSource.transfer(param.accountId, param.transferBody)

}