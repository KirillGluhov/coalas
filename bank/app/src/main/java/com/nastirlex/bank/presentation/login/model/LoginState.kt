package com.nastirlex.bank.presentation.login.model

import com.nastirlex.bank.presentation.core.ScreenState

data class LoginState(
    val email: String,
    val password: String,
) : ScreenState