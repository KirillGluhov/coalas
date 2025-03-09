package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.Tariff
import javax.inject.Inject

interface GetTariffsUseCase : SuspendedUseCase<Unit, List<Tariff>>

class GetTariffsUseCaseImpl @Inject constructor(
    private val coreDataSource: CoreDataSource
) : GetTariffsUseCase {
    override suspend fun execute(param: Unit): List<Tariff> =
        coreDataSource.getTariffs()
}