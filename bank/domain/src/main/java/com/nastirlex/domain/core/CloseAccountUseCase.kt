package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import javax.inject.Inject

interface CloseAccountUseCase : SuspendedUseCase<String, Unit>

class CloseAccountUseCaseImpl @Inject constructor(private val coreDataSource: CoreDataSource) :
    CloseAccountUseCase {
    override suspend fun execute(param: String) =
        coreDataSource.closeAccount(param)

}