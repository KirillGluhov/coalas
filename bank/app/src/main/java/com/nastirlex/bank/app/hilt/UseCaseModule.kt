package com.nastirlex.bank.app.hilt

import com.nastirlex.domain.core.CloseAccountUseCase
import com.nastirlex.domain.core.CloseAccountUseCaseImpl
import com.nastirlex.domain.core.CreateLoanUseCase
import com.nastirlex.domain.core.CreateLoanUseCaseImpl
import com.nastirlex.domain.core.GetAccountsUseCase
import com.nastirlex.domain.core.GetAccountsUseCaseImpl
import com.nastirlex.domain.core.GetLoanRatingUseCase
import com.nastirlex.domain.core.GetLoanRatingUseCaseImpl
import com.nastirlex.domain.core.GetLoanUseCase
import com.nastirlex.domain.core.GetLoanUseCaseImpl
import com.nastirlex.domain.core.GetLoansUseCase
import com.nastirlex.domain.core.GetLoansUseCaseImpl
import com.nastirlex.domain.core.GetTariffsUseCase
import com.nastirlex.domain.core.GetTariffsUseCaseImpl
import com.nastirlex.domain.core.GetTransactionsUseCase
import com.nastirlex.domain.core.GetTransactionsUseCaseImpl
import com.nastirlex.domain.core.OpenAccountUseCase
import com.nastirlex.domain.core.OpenAccountUseCaseImpl
import com.nastirlex.domain.core.ReplenishAccountUseCase
import com.nastirlex.domain.core.ReplenishAccountUseCaseImpl
import com.nastirlex.domain.core.ReplenishLoanUseCase
import com.nastirlex.domain.core.ReplenishLoanUseCaseImpl
import com.nastirlex.domain.core.TransferUseCase
import com.nastirlex.domain.core.TransferUseCaseImpl
import com.nastirlex.domain.core.WithdrawAccountUseCase
import com.nastirlex.domain.core.WithdrawAccountUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCustomerUseCase(getCustomerUseCaseImpl: GetAccountsUseCaseImpl): GetAccountsUseCase

    @Binds
    fun bindGetTransactionsUseCase(getTransactionsUseCaseImpl: GetTransactionsUseCaseImpl): GetTransactionsUseCase

    @Binds
    fun openAccountUseCase(openAccountUseCaseImpl: OpenAccountUseCaseImpl): OpenAccountUseCase

    @Binds
    fun closeAccountUseCase(closeAccountUseCaseImpl: CloseAccountUseCaseImpl): CloseAccountUseCase

    @Binds
    fun replenishAccountUseCase(replenishAccountUseCaseImpl: ReplenishAccountUseCaseImpl): ReplenishAccountUseCase

    @Binds
    fun withdrawAccountUseCase(withdrawAccountUseCaseImpl: WithdrawAccountUseCaseImpl): WithdrawAccountUseCase

    @Binds
    fun getTariffsUseCase(getTariffsUseCaseImpl: GetTariffsUseCaseImpl): GetTariffsUseCase


    @Binds
    fun transferUseCase(transferUseCaseImpl: TransferUseCaseImpl): TransferUseCase

    @Binds
    fun getLoansUseCase(getLoansUseCaseImpl: GetLoansUseCaseImpl): GetLoansUseCase

    @Binds
    fun createLoanUseCase(createLoanUseCaseImpl: CreateLoanUseCaseImpl): CreateLoanUseCase

    @Binds
    fun getLoanUseCase(getLoanUseCaseImpl: GetLoanUseCaseImpl): GetLoanUseCase

    @Binds
    fun replenishLoanUseCase(replenishLoanUseCaseImpl: ReplenishLoanUseCaseImpl): ReplenishLoanUseCase

    @Binds
    fun getLoanRating(getLoanRatingUseCaseImpl: GetLoanRatingUseCaseImpl): GetLoanRatingUseCase

}