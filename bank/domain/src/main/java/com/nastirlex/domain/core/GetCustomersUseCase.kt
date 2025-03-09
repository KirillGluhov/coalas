package com.nastirlex.domain.core

import com.nastirlex.domain.common.SuspendedUseCase
import com.nastirlex.domain.core.model.Customer
import javax.inject.Inject

interface GetCustomersUseCase : SuspendedUseCase<Unit, List<Customer>>

//class GetCustomersUseCaseImpl @Inject constructor(
//    private val coreDataSource: CoreDataSource
//) : GetCustomersUseCase {
//    override suspend fun execute(param: Unit): List<Customer> =
//        listOf(Custo)
//}