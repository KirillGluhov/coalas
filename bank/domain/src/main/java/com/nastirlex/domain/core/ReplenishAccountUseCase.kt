package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.OperationAccountParam
import javax.inject.Inject

interface ReplenishAccountUseCase : SuspendedUseCase<OperationAccountParam, Unit>

class ReplenishAccountUseCaseImpl @Inject constructor(private val coreDataSource: CoreDataSource) :
    ReplenishAccountUseCase {
    override suspend fun execute(param: OperationAccountParam) {
        coreDataSource.replenishAccount(param.accountId, param.operationAccount)
    }
}