package com.nastirlex.bank.presentation.loanInfo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nastirlex.bank.R
import com.nastirlex.bank.presentation.loanInfo.model.LoanInfoState
import com.nastirlex.bank.presentation.newAccounts.AccountIdDropDownMenu
import com.nastirlex.domain.core.model.Loan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanInfoScreen(
    viewModel: LoanInfoViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    val sheetState = rememberModalBottomSheetState(true)

    Scaffold { contentPadding ->
        Content(screenState.currentState, viewModel, modifier = Modifier.padding(contentPadding))

        if (screenState.currentState.showSelectAccountBottomSheet)
            SelectAccountForAutoDebtBottomSheet(
                screenState.currentState,
                sheetState,
                { isVisible -> viewModel.onAccountForAutoDebtBottomSheetChangeVisibility(isVisible) },
                { accountId -> viewModel.onAccountIdSelected(accountId) }
            )
    }
}

@Composable
private fun Content(
    state: LoanInfoState,
    viewModel: LoanInfoViewModel,
    modifier: Modifier = Modifier,
) {
    LoanCard(
        state.loan,
        onReplenishLoanButtonClick = { viewModel.replenishLoan() },
        turnOnAutoDebtButtonCLick = { viewModel.onAccountForAutoDebtBottomSheetChangeVisibility(true) },
        turnOffAutoDebtButtonCLick = { viewModel.turnOffAutoDebt() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoanCard(
    loan: Loan,
    onReplenishLoanButtonClick: () -> Unit,
    turnOnAutoDebtButtonCLick: () -> Unit,
    turnOffAutoDebtButtonCLick: () -> Unit,
) {
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Column {
                Text(
                    text = "Тариф: ${loan.tariff.name}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Тарифная ставка: ${loan.tariff.procent}%",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Сумма кредита: ${loan.size}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Долг по кредиту: ${loan.debt}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Процентная ставка: ${loan.procents}%",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Дата взятия: ${loan.openDate}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Предварительная дата закрытия: ${loan.closeDate}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Сумма выплаченных платежей: ${loan.payout}",
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "Включен ли автоплатеж: ${loan.isAutodebt}",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Column {
                Icon(
                    painter = painterResource(R.drawable.ic_replenish),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onReplenishLoanButtonClick() }
                )

               TextButton(onClick = if (loan.isAutodebt) turnOffAutoDebtButtonCLick else turnOnAutoDebtButtonCLick) {
                   Text(stringResource(if (loan.isAutodebt) R.string.turn_off_auto_debt else R.string.turn_on_auto_debt))
               }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAccountForAutoDebtBottomSheet(
    state: LoanInfoState,
    sheetState: SheetState,
    onBottomSheetChangeVisibility: (Boolean) -> Unit,
    onAccountIdClick: (String) -> Unit,
) {
    var accountIdExpanded by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = { onBottomSheetChangeVisibility(false) },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            AccountIdDropDownMenu(
                selectedAccountId = state.selectedAccountId,
                accountIdExpanded = accountIdExpanded,
                accounts = state.accounts,
                changeAccountIdExpanded = { value -> accountIdExpanded = value},
                onAccountIdClick = onAccountIdClick,
            )

        }
    }
}