package com.nastirlex.bank.presentation.common.navigation

abstract class Destination {
    val dest: String
        get() = javaClass.simpleName

    val route: String by lazy {
        buildString {
            append(dest)
            args().forEach { arg ->
                append("/{$arg}")
            }
        }
    }

    open fun args(): List<String> = emptyList()

    fun destWithArgs(vararg args: Any?) = buildString {
        append(dest)
        args.forEach { arg ->
            append("/$arg")
        }
    }
}