package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.AccountTransactions
import javax.inject.Inject

interface GetTransactionsUseCase : SuspendedUseCase<Int, AccountTransactions>

class GetTransactionsUseCaseImpl @Inject constructor(private val coreDataSource: CoreDataSource) :
    GetTransactionsUseCase {
    override suspend fun execute(param: Int): AccountTransactions =
        coreDataSource.getTransactions(param)

}