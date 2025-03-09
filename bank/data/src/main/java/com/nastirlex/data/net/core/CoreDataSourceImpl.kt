package com.nastirlex.data.net.core

import com.nastirlex.data.net.core.model.toDomain
import com.nastirlex.data.net.core.model.toDto
import com.nastirlex.domain.core.CoreDataSource
import com.nastirlex.domain.core.model.AccountTransactions
import com.nastirlex.domain.core.model.CreateLoan
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.Loan
import com.nastirlex.domain.core.model.OpenAccount
import com.nastirlex.domain.core.model.OperationAccount
import com.nastirlex.domain.core.model.Rating
import com.nastirlex.domain.core.model.ShortLoan
import com.nastirlex.domain.core.model.Tariff
import com.nastirlex.domain.core.model.Transfer
import javax.inject.Inject

class CoreDataSourceImpl @Inject constructor(private val coreApi: CoreApi) : CoreDataSource {
    override suspend fun getAccounts(): List<Account> =
        coreApi.getAccounts().map { it.toDomain() }

    override suspend fun openAccount(openAccount: OpenAccount) =
        coreApi.openAccount()

    override suspend fun closeAccount(accountId: String) {
        coreApi.closeAccount(accountId = accountId)
    }

    override suspend fun replenishAccount(accountId: String, operationAccount: OperationAccount) =
        coreApi.replenishAccount(
            accountId = accountId,
            replenishAccountBody = operationAccount.toDto()
        )

    override suspend fun withdrawAccount(accountId: String, operationAccount: OperationAccount) =
        coreApi.withdrawAccount(accountId = accountId, withdrawAccountBody = operationAccount.toDto())

    override suspend fun getTariffs(): List<Tariff> =
        coreApi.getTariffs().map { it.toDomain() }

    override suspend fun getLoans(): List<ShortLoan> =
        coreApi.getLoans().map { it.toDomain() }

    override suspend fun createLoan(createLoan: CreateLoan) =
        coreApi.createLoan(createLoanBody = createLoan.toDto())




    override suspend fun getTransactions(accountId: Int): AccountTransactions =
        coreApi.getTransactions(account = accountId).toDomain()
    override suspend fun transfer(accountId: String, transferBody: Transfer) =
        coreApi.transfer(accountId = accountId, transferBody = transferBody.toDto())

    override suspend fun getLoan(loanId: String): Loan =
        coreApi.getLoan(loanId = loanId).toDomain()

    override suspend fun getLoanRating(userId: Int): Rating =
        coreApi.getLoanRating(userId = userId).toDomain()
}