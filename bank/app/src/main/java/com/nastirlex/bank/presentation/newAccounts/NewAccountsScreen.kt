package com.nastirlex.bank.presentation.newAccounts

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.common.collect.ListMultimap
import com.nastirlex.bank.R
import com.nastirlex.bank.presentation.newAccounts.model.NewAccountsState
import com.nastirlex.domain.core.model.Account
import com.nastirlex.domain.core.model.Loan
import com.nastirlex.domain.core.model.Tariff
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewAccountsScreen(
    viewModel: NewAccountsViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()
    val sheetState = rememberModalBottomSheetState(true)

    Scaffold { contentPadding ->
        Content(
            screenState.currentState,
            { tariffId -> viewModel.onTariffClick(tariffId) },
            modifier = Modifier.padding(contentPadding)
        )

        if (screenState.currentState.showCreateLoanBottomSheet)
            CreateLoanBottomSheet(
                screenState.currentState,
                sheetState,
                onAccountIdClick = { accountId -> viewModel.onAccountIdSelected(accountId) },
                onLoanSizeChanged = { newSize -> viewModel.onLoanSizeChanged(newSize) },
                onCreateLoanButtonClick = { viewModel.createLoan() },
                onBottomSheetChangeVisibility = { isVisible -> viewModel.onBottomSheetChangeVisibility(isVisible) }
            )

    }
}

@Composable
private fun Content(
    state: NewAccountsState,
    onTariffClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(text = "Тарифы")
        }
//        item {
//            Text("Кредитный рейтинг: ${screenState.currentState.rating}")
//        }
        items(state.tariffs) { tariff ->
            Tariff(tariff, onTariffClick = { onTariffClick(tariff.id) })
        }
//        items(screenState.currentState.rates) { rate ->
//            Rate(rate = "${rate.rate} %", onCreditClick = {})
//        }

    }
}

@Composable
private fun Tariff(tariff: Tariff, onTariffClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onTariffClick() }
    ) {
        Image(painter = painterResource(id = R.drawable.account), contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = tariff.name,
                color = MaterialTheme.colorScheme.primary,
            )
            Row {
                Text(text = "Процентная ставка")
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = tariff.procents, modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Divider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateLoanBottomSheet(
    state: NewAccountsState,
    sheetState: SheetState,
    onAccountIdClick: (String) -> Unit,
    onLoanSizeChanged: (String) -> Unit,
    onCreateLoanButtonClick: (String) -> Unit,
    onBottomSheetChangeVisibility: (Boolean) -> Unit
) {
    var accountIdExpanded by remember { mutableStateOf(false) }
    var showModal by remember { mutableStateOf(false) }

    var selectedDateMillis by remember { mutableStateOf<Long?>(null) }
    val selectedDate = selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""
    val selectedZonedDateTime = selectedDateMillis?.let {
        convertMillisToZonedDateTime(it)
    }

    ModalBottomSheet(
        onDismissRequest = { onBottomSheetChangeVisibility(false) },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                stringResource(R.string.account_for_loan_choice),
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(Modifier.height(8.dp))

            AccountIdDropDownMenu(
                selectedAccountId = state.selectedAccountId,
                accountIdExpanded = accountIdExpanded,
                accounts = state.accounts,
                changeAccountIdExpanded = { value -> accountIdExpanded = value},
                onAccountIdClick = onAccountIdClick,
            )

            Spacer(Modifier.height(8.dp))
            TextField(
                value = state.loanSize,
                onValueChange = { textFieldValue -> onLoanSizeChanged(textFieldValue) },
                modifier = Modifier
                    .fillMaxWidth(),
                label = { Text(stringResource(R.string.loan_size_label)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = selectedDate,
                onValueChange = { },
                label = { Text(stringResource(R.string.loan_close_date_label)) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showModal = !showModal }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
            )

            if (showModal) {
                DatePickerModal(
                    onDateSelected = { selectedDateMillis = it },
                    onDismiss = { showModal = false }
                )
            }
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    if (selectedZonedDateTime != null) {
                        onBottomSheetChangeVisibility(false)
                        onCreateLoanButtonClick(selectedZonedDateTime)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(stringResource((R.string.create_loan_button)))
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(stringResource(R.string.date_picker_ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.date_picker_cancel))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
private fun Loan(loan: Loan) {
    Card(modifier = Modifier) {
        Row {
            Text(text = "Ставка")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = loan.procents.toString() + " %")
        }
        Text(text = "Сумма: " + loan.size.toString())
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Платежи")
        Column {
//            loan.payments.forEach {
//                Transaction(type = it.date, status = it.status, amount = it.amount.toString())
//            }
        }

    }
}

@Composable
fun AccountIdDropDownMenu(
    selectedAccountId: String,
    accountIdExpanded: Boolean,
    accounts: List<Account>,
    changeAccountIdExpanded: (Boolean) -> Unit,
    onAccountIdClick: (String) -> Unit,
) {
    val selectedAccount = accounts.find { it.id == selectedAccountId }
    val accountValue = selectedAccount?.let { "${selectedAccount.balance} RUB ${selectedAccount.status}" } ?: ""

    TextField(
        value = accountValue,
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .clickable { changeAccountIdExpanded(true) },
        readOnly = true,
        trailingIcon = {
            Box {
                IconButton(onClick = { changeAccountIdExpanded(true) }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = accountIdExpanded,
                    onDismissRequest = { changeAccountIdExpanded(false) }
                ) {
                    accounts.forEach {
                        DropdownMenuItem(
                            text = { Text(text = "${it.balance} RUB ${it.status}") },
                            onClick = {
                                onAccountIdClick(it.id)
                                changeAccountIdExpanded(false)
                            }
                        )
                    }
                }
            }
        }
    )
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

fun convertMillisToZonedDateTime(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.mmm'Z'", Locale.getDefault())
    return formatter.format(Date(millis))
}