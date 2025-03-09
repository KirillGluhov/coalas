package com.nastirlex.bank.presentation.login

import android.content.Intent
import com.nastirlex.bank.presentation.MainActivity
import com.nastirlex.bank.presentation.core.StatefulViewModel
import com.nastirlex.bank.presentation.login.model.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor()  : StatefulViewModel<LoginState>() {
    override fun createInitialState(): LoginState =
        LoginState(
            email = "",
            password = "",
        )

    fun onEmailChange(email: String) {
        updateState {
            copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        updateState {
            copy(password = password)
        }
    }


}