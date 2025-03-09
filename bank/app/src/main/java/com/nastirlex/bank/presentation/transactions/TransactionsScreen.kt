package com.nastirlex.bank.presentation.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nastirlex.bank.R
import com.nastirlex.bank.presentation.ui.theme.Gray

@Composable
fun TransactionsScreen(
    viewModel: TransactionsViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        screenState.currentState.transactions.forEach { (day, transactions) ->
            item {
                Text(text = day)
            }

            items(transactions) { transaction ->
                Transaction(
                    type = transaction.type,
                    status = transaction.status,
                    amount = transaction.amount
                )
            }
        }

    }
}

@Composable
fun Transaction(type: String, status: String, amount: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Column {
            Row {
                Text(text = status)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "$amount Р")
            }
            Text(text = type)
            Divider(thickness = 1.dp, color = Gray)
        }
    }
}