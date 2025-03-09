package com.nastirlex.bank.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class StatefulViewModel<State : ScreenState> : ViewModel() {

  private val _screenState by lazy { MutableStateFlow(StateChangeUnit(null, createInitialState())) }
  val screenState: StateFlow<StateChangeUnit<State>>
    get() = _screenState

  val currentScreenState: State get() = _screenState.value.currentState

  /**
   * Очередь событий, которые должны быть обработаны один раз.
   * Например: навигация, показ диалогов или снэкбаров
   */
  val screenEvents: EventQueue = EventQueue()

  fun updateState(transform: suspend State.() -> State) {
    viewModelScope.launch {
      val current = currentScreenState
      val new = transform.invoke(current)
      _screenState.emit(StateChangeUnit(previousState = current, currentState = new))
      // handleStateChange(current, new)
    }
  }

  fun offerEvent(event: Event) {
    screenEvents.offerEvent(event)
  }

  abstract fun createInitialState(): State
}
