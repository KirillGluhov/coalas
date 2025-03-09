package com.nastirlex.domain.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface SuspendedUseCase<in Input, Output> {
    suspend operator fun invoke(param: Input): Result<Output> =
    withContext(Dispatchers.IO) {
        try {
            Result.success(execute(param))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun execute(param: Input): Output
}