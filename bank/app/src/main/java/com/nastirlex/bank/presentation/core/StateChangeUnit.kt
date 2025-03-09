package com.nastirlex.bank.presentation.core

data class StateChangeUnit<State : ScreenState>(
  val previousState: State?,
  val currentState: State
)
