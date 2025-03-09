package com.nastirlex.data.net.core

import com.nastirlex.data.net.core.model.AccountTransactionsDto
import com.nastirlex.data.net.core.model.AccountDto
import com.nastirlex.data.net.core.model.CreateLoanDto
import com.nastirlex.data.net.core.model.LoanDto
import com.nastirlex.data.net.core.model.ShortLoanDto
import com.nastirlex.data.net.core.model.OperationAccountDto
import com.nastirlex.data.net.core.model.TariffDto
import com.nastirlex.data.net.core.model.RatingDto
import com.nastirlex.data.net.core.model.TransferBodyDto
import com.nastirlex.data.net.core.model.UserDto
import com.nastirlex.domain.core.model.Tariff
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CoreApi {

    // ---------- СЧЕТА -------------

    // получение счетов
    @GET("api/accounts/my")
    suspend fun getAccounts(
        @Header("Idempotency-key") key: String = "07c4fd27-43ec-4e69-a291-2194901505f8",
    ): List<AccountDto> = listOf(AccountDto(), AccountDto())

    // открытие счета
    @POST("api/accounts")
    suspend fun openAccount(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
//        @Body openAccountBody: OpenAccountDto
    )

    // закрытие счета
    @DELETE("api/accounts/{accountId}")
    suspend fun closeAccount(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("accountId") accountId: String,
    )

    // пополнение счета
    @PUT("api/accounts/{accountId}/replenish")
    suspend fun replenishAccount(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("accountId") accountId: String,
        @Body replenishAccountBody: OperationAccountDto
    )

    // снятие денег со счета
    @POST("api/accounts/{accountId}/withdraw")
    suspend fun withdrawAccount(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("accountId") accountId: String,
        @Body withdrawAccountBody: OperationAccountDto
    )


    // ---------- КРЕДИТЫ -------------

    /**
    * ВЗЯТЬ КРЕДИТ
    */
    @PUT("api/loan/{loan}")
    suspend fun createLoan(
        @Header("Idempotency-key") key: String = "07c4fd27-43ec-4e69-a291-2194901505f8",
        @Body createLoanBody: CreateLoanDto
    )

    /**
     * ВСЕ МОИ КРЕДИТЫ
     */
    @GET("api/loans/my")
    suspend fun getLoans(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
    ): List<ShortLoanDto> = listOf(ShortLoanDto())

    /**
     * ИНФОРМАЦИЯ О КОНКРЕТНОМ КРЕДИТЕ
     */
    @GET("api/loans/{loanId}")
    suspend fun getLoan(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("loanId") loanId: String,
    ): LoanDto = LoanDto()

    /**
     * ВНЕСТИ ПЛАТЕЖ ПО КРЕДИТУ
     */
    @PUT("api/loans/{loanId}")
    suspend fun replenishLoan(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("loanId") loanId: String,
        @Body transferBody: TransferBodyDto,
    )

    /**
     * ВКЛЮЧИТЬ АВТОПОПОЛНЕНИЕ КРЕДИТА С ОПРЕДЕЛЕННОГО СЧЕТА
     */
    @PUT("api/loans/{loanId}/accounts/{accountId}/autodebt")
    suspend fun turnOnAutoDebt(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("loanId") loanId: String,
        @Path("accountId") accountId: String,
    )

    /**
     * ВЫКЛЮЧИТЬ АВТОПОПОЛНЕНИЕ КРЕДИТА С ОПРЕДЕЛЕННОГО СЧЕТА
     */
    @DELETE("api/loans/{loanId}/autodebt")
    suspend fun turnOffAutoDebt(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
        @Path("loanId") loanId: String,
    )

    // ---------- ТАРИФЫ -------------

    /**
     * ПОЛУЧИТЬ ВСЕ ТАРИФЫ
     */
    @GET("api/tariffs")
    suspend fun getTariffs(
        @Header("Authorization") token: String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiMDNmYWU5MjRlMDMwNTRlNmM0OGYzYTZhZDY1NGE1NzY4YmQ2ZmU5Mjk2N2FkNGViYTViN2EwNTRkOWE2NzhkODM0OTczMWI5NTRhOGI0YzUiLCJpYXQiOjE3MTE3NzIxOTUuMjU1NDkxLCJuYmYiOjE3MTE3NzIxOTUuMjU1NDk2LCJleHAiOjE3NDMzMDgxOTUuMjM0ODAzLCJzdWIiOiIxMSIsInNjb3BlcyI6W119.hbSFqDF7Yf-vVsizL-ldQeIyB0hFo9d6svnHi7MwecUjFQkPlNZYRQCUeuazk1DI1P20qhW4kRR5DE9Pn1dxFZ74_74WT-IxO8_Vo_iG9BUqPwSyUzyV60lkZ3w7-QtzNcaFVyXonW9Hvh_guxM3Yzm21TzkYeIJJ8eM59Lv_f5NE8iD3snueqlweblU-RH3teBlr3718OXH5P38jzG2BnQUeBPbNy2L_Llu3XqF8m8ebWd8fILbWYXtC7zBMZYXvR09yG8f1Gn047_1Ebx_ukU4m-v3MUmxCbOSvVJ6U2dOLmvXFez-Bw5Jf3ta_dqhQbpF8PhEKRsJfddKWPqECUXCEdlObDfZqn5KxKwTgJ_JYlHlhD2qdh9D_O96KZ9l7AxHTOcG_IgzJldAMlwM_6zN4EgOpOF0DHRSMV-87ZcisdxB_AdXblkq7DelUnD_tk1NaL4hHb08jXdF4IzJFXyAwAA_LA9csIVoegyczlKlUV_8CnADQDYChZStlBk7suja9p3jwa63IbJ_UsawEZZ0yCPt6lyVmIt-YUSYthx_0Q1ht8pvYxRmQhR1LIFwqNpdbCHLP9WhobTK4TK--ucj61VEksauCTKeOG8_vJ6sdX2CkKG9x112U3Qr93lnQzxl9zTVJt3XhLyzsZ1ho55PFwUltaJK0Lu0-S9Fhl0",
    ): List<TariffDto> = listOf(TariffDto())



    @GET("api/rating/{user}")
    suspend fun getLoanRating(
        @Header("Idempotency-key") key: String = "07c4fd27-43ec-4e69-a291-2194901505f8",
        @Path("user") userId: Int,
    ): RatingDto = RatingDto()

    @GET("api/account/{account}/transaction")
    suspend fun getTransactions(
        @Header("Idempotency-key") key: String = "07c4fd27-43ec-4e69-a291-2194901505f8",
        @Path("account") account: Int
    ): AccountTransactionsDto = AccountTransactionsDto()

    @GET("api/users/{user}")
    suspend fun getUser(
        @Path("user") user: Int,
        @Header("Idempotency-key") key: String = "07c4fd27-43ec-4e69-a291-2194901505f8",
    ): UserDto = UserDto()

    @POST("api/account/{id}/transfer")
    suspend fun transfer(
        @Header("Idempotency-key") key: String = "07c4fd27-43ec-4e69-a291-2194901505f8",
        @Path("id") accountId: String,
        @Body transferBody: TransferBodyDto,
    )
}