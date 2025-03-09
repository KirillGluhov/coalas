package com.nastirlex.bank.presentation.transferSecondStep

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nastirlex.bank.presentation.transactions.model.TransactionsScreenEvent
import com.nastirlex.bank.presentation.transferSecondStep.model.TransferSecondStepScreenEvent

@Composable
fun TransferSecondStepScreen(
    navigateToMainScreen: () -> Unit,
    viewModel: TransferSecondStepViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()

    val context = LocalView.current.context

    viewModel.screenEvents.CollectEvent { event ->
        when (event) {
            is TransferSecondStepScreenEvent.OpenMainScreen -> navigateToMainScreen()
            is TransferSecondStepScreenEvent.ShowError -> Toast.makeText(
                context,
                event.error,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    var writeOffExpanded by remember { mutableStateOf(false) }
    var recipientExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(text = "Счет списания")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = screenState.currentState.writeOffValue,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                Box {
                    IconButton(onClick = { writeOffExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                    DropdownMenu(
                        expanded = writeOffExpanded,
                        onDismissRequest = { writeOffExpanded = false }
                    ) {
                        screenState.currentState.userAccounts.forEach {
                            DropdownMenuItem(
                                text = { Text(text = "it.currency" + " " + it.id) },
                                onClick = {
                                    viewModel.onUserAccountClick(it)
                                    writeOffExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Счет получения")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = screenState.currentState.receiptValue, onValueChange = {}, readOnly = true,
            trailingIcon = {
                Box {
                    IconButton(onClick = { recipientExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }

                    DropdownMenu(
                        expanded = recipientExpanded,
                        onDismissRequest = { recipientExpanded = false }
                    ) {
                        screenState.currentState.recipientAccounts.forEach {
                            DropdownMenuItem(
                                text = { Text(text = "it.currency" + " " + it.id) },
                                onClick = {
                                    viewModel.onRecipientAccountClick(it)
                                    recipientExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Сумма")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = screenState.currentState.amount,
            onValueChange = { viewModel.onAmountChange(it) }
        )

        TextButton(
            onClick = { viewModel.onTransferButtonClick() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Перевести")
        }
    }
}