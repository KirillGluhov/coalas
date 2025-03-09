package com.nastirlex.bank.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nastirlex.bank.R
import com.nastirlex.bank.presentation.core.StateChangeUnit
import com.nastirlex.bank.presentation.main.model.MainScreenEvent
import com.nastirlex.bank.presentation.main.model.MainState
import com.nastirlex.bank.presentation.transactions.model.TransactionsScreenEvent
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.ShortLoan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigateToNewAccounts: () -> Unit,
    navigateToTransactions: (accountId: Int) -> Unit,
    navigateToTransferFirstStep: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    val state = screenState.currentState
    val sheetState = rememberModalBottomSheetState()

    viewModel.screenEvents.CollectEvent { event ->
        when (event) {
            is TransactionsScreenEvent.OpenTransactionsScreen -> {

            }
                is TransactionsScreenEvent.OpenReplenishBottomSheet -> {}
//                navigateToTransactions(event.accountId)
            is MainScreenEvent.OpenReplenishAccountBottomSheet -> {

            }
        }
    }

    Scaffold { contentPadding ->
        Content(
            screenState,
            viewModel,
            navigateToNewAccounts,
            Modifier.padding(contentPadding),
        )

        if (state.showWithdrawAccountBottomSheet) {
            AmountBottomSheet(
                isReplenish = false,
                amountValue = state.amountEditValue,
                onAmountValueChange = { newValue -> viewModel.onAmountValueChange(newValue) },
                sheetState = sheetState,
                changeBottomSheetVisibility = { show -> viewModel.updateWithdrawAccountBottomSheetVisibility(show) },
                onSaveButtonClick = { viewModel.withdrawAccount() }
            )
        }

        if (state.showReplenishAccountBottomSheet) {
            AmountBottomSheet(
                isReplenish = true,
                amountValue = state.amountEditValue,
                onAmountValueChange = { newValue -> viewModel.onAmountValueChange(newValue) },
                sheetState = sheetState,
                changeBottomSheetVisibility = { show -> viewModel.updateReplenishAccountBottomSheetVisibility(show) },
                onSaveButtonClick = { viewModel.replenishAccount() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountBottomSheet(
    isReplenish: Boolean,
    amountValue: String,
    onAmountValueChange: (String) -> Unit,
    sheetState: SheetState,
    changeBottomSheetVisibility: (Boolean) -> Unit,
    onSaveButtonClick: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = { changeBottomSheetVisibility(false) },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                stringResource(if (isReplenish) R.string.replenish_money_label else R.string.withdraw_money_label),
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = amountValue,
                onValueChange = { textFieldValue -> onAmountValueChange(textFieldValue) },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(stringResource(R.string.amount_label)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onSaveButtonClick,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(stringResource((if (isReplenish) R.string.replenish_button else R.string.withdraw_button)))
            }
        }
    }
}

@Composable
fun Content(
    state: StateChangeUnit<MainState>,
    viewModel: MainViewModel,
    navigateToNewAccounts: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Счета", color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.weight(1f))
                PlusIcon { viewModel.openAccount() }
            }
        }

        itemsIndexed(state.currentState.accounts) { index, account ->
            AccountCard(
                account = account,
                onAccountClick = { viewModel.onAccountClick(accountId = account.id) },
                replenishAccount = { viewModel.openReplenishAccountBottomSheet(account.id) },
                withdrawAccount = { viewModel.openWithdrawAccountBottomSheet(accountId = account.id) },
                closeAccount = { viewModel.closeAccount(accountId = account.id) },
                onVisibilityChange = {
                    viewModel.onAccountVisibilityChange(
                        index,
                        isCredit = false,
                        account = account
                    )
                }
            )
        }

        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Кредиты", color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.weight(1f))
                PlusIcon(navigateToNewAccounts)
            }
        }

        itemsIndexed(state.currentState.loans) { index, loan ->
            LoanCard(
                loan = loan,
                onLoanClick = {  },
            )
        }

//        item {
//            TextButton(onClick = navigateToTransferFirstStep) {
//                Text(text = "Платежи и переводы", color = MaterialTheme.colorScheme.primary)
//            }
//        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCard(
    account: Account,
    onAccountClick: () -> Unit,
    replenishAccount: () -> Unit,
    withdrawAccount: () -> Unit,
    closeAccount: () -> Unit,
    onVisibilityChange: () -> Unit,
) {
    Card(
        onClick = onAccountClick,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.card),
                contentDescription = null,
                modifier = Modifier.size(width = 68.dp, height = 44.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (!account.isHidden) "${account.balance} Р" else "• • • •",
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = account.status,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                val date = account.closeDate.split(' ')
                Text(
                    text = date[0],
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Icon(
                    painter = painterResource(id = if (!account.isHidden) R.drawable.ic_open_eye else R.drawable.ic_closed_eye),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onVisibilityChange() }
                )
                Icon(
                    painter = painterResource(R.drawable.ic_replenish),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { replenishAccount() }
                )
                Icon(
                    painter = painterResource(R.drawable.ic_debit),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { withdrawAccount() }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { closeAccount() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanCard(
    loan: ShortLoan,
    onLoanClick: () -> Unit,
) {
    Card(
        onClick = onLoanClick,
        modifier = Modifier
            .fillMaxWidth()
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
        }
    }
}

@Composable
fun PlusIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp, 26.dp)
        )
    }
}