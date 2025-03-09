package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.OperationAccountParam
import javax.inject.Inject

interface WithdrawAccountUseCase : SuspendedUseCase<OperationAccountParam, Unit>

class WithdrawAccountUseCaseImpl @Inject constructor(private val coreDataSource: CoreDataSource) : WithdrawAccountUseCase {
    override suspend fun execute(param: OperationAccountParam) {
        coreDataSource.withdrawAccount(param.accountId, param.operationAccount)
    }
}