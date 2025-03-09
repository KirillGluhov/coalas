package com.nastirlex.domain.core

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

interface CoreDataSource {
    suspend fun getAccounts(): List<Account>

    suspend fun getLoans() : List<ShortLoan>

    suspend fun openAccount(openAccount: OpenAccount)

    suspend fun closeAccount(accountId: String)

    suspend fun replenishAccount(accountId: String, operationAccount: OperationAccount)
    suspend fun withdrawAccount(accountId: String, operationAccount: OperationAccount)

    suspend fun getTariffs(): List<Tariff>

    suspend fun createLoan(createLoan: CreateLoan)


    suspend fun transfer(accountId: String, transferBody: Transfer)


    suspend fun getTransactions(accountId: Int): AccountTransactions

    suspend fun getLoan(loanId: String) : Loan

    suspend fun getLoanRating(userId: Int): Rating
}