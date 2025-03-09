package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.Account
import javax.inject.Inject

interface GetAccountsUseCase : SuspendedUseCase<Unit, List<Account>>

class GetAccountsUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
): GetAccountsUseCase {
    override suspend fun execute(param: Unit): List<Account> =
        coreDataSource.getAccounts()
}