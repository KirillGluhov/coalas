package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.OpenAccount
import javax.inject.Inject

interface OpenAccountUseCase : SuspendedUseCase<OpenAccount, Unit>

class OpenAccountUseCaseImpl @Inject constructor(private val coreDataSource: CoreDataSource) : OpenAccountUseCase {
    override suspend fun execute(param: OpenAccount) {
        coreDataSource.openAccount(param)
    }
}