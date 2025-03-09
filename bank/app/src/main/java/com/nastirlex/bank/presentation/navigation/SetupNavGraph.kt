package com.nastirlex.bank.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nastirlex.bank.presentation.login.LoginScreen
import com.nastirlex.bank.presentation.login.navigation.LoginDestination
import com.nastirlex.bank.presentation.main.MainScreen
import com.nastirlex.bank.presentation.main.navigation.MainDestination
import com.nastirlex.bank.presentation.newAccounts.NewAccountsScreen
import com.nastirlex.bank.presentation.newAccounts.navigation.NewAccountsDestination
import com.nastirlex.bank.presentation.transactions.TransactionsScreen
import com.nastirlex.bank.presentation.transactions.navigation.TransactionsDestination
import com.nastirlex.bank.presentation.transferFirstStep.TransferFirstStepScreen
import com.nastirlex.bank.presentation.transferFirstStep.destination.TransferFirstStepDestination
import com.nastirlex.bank.presentation.transferSecondStep.TransferSecondStepScreen
import com.nastirlex.bank.presentation.transferSecondStep.navigation.TransferSecondStepDestination

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainDestination.route) {
        composable(
            route = LoginDestination.route,
        ) {
            LoginScreen()
        }

        composable(
            route = TransferFirstStepDestination.route,
        ) {
            TransferFirstStepScreen(navigateToSecondStep = {
                navController.navigate(
                    TransferSecondStepDestination.destWithArgs(it)
                )
            })
        }

        composable(
            route = TransferSecondStepDestination.route,
            arguments = listOf(
                navArgument(TransferSecondStepDestination.recipientId) {
                    type = NavType.IntType
                }
            )
        ) {
            TransferSecondStepScreen(navigateToMainScreen = { navController.navigate(MainDestination.dest) })
        }

        composable(
            route = TransactionsDestination.route,
            arguments = listOf(navArgument(TransactionsDestination.accountIdArg) {
                type = NavType.IntType
            })
        ) {
            TransactionsScreen()
        }

        composable(
            route = MainDestination.route
        ) {
            MainScreen(
                navigateToNewAccounts = { navController.navigate(NewAccountsDestination.dest) },
                navigateToTransactions = { accountId ->
                    navController.navigate(
                        TransactionsDestination.destWithArgs(accountId)
                    )
                },
                navigateToTransferFirstStep = { navController.navigate(TransferFirstStepDestination.dest) }
            )
        }

        composable(route = NewAccountsDestination.route) {
            NewAccountsScreen()
        }
    }
}