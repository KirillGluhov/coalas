package com.nastirlex.bank.presentation.transferFirstStep

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nastirlex.bank.R
import com.nastirlex.bank.presentation.ui.theme.Gray

@Composable
fun TransferFirstStepScreen(
    navigateToSecondStep: (Int) -> Unit,
    viewModel: TransferFirstStepViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Платежи и переводы")
        Spacer(modifier = Modifier.height(16.dp))
        ///////////////////// НАЧАЛО КАРУСЕЛИ
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable(onClick = { navigateToSecondStep(1) })
            ) {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Между счетами", textAlign = TextAlign.Center)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Себе в другой банк", textAlign = TextAlign.Center)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Что-то ещё", textAlign = TextAlign.Center)
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Что-то ещё", textAlign = TextAlign.Center)
            }
        }
        ///////////////////// КОНЕЦ КАРУСЕЛИ
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Контакты")
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(screenState.currentState.contacts) {
                Contact(
                    name = it.name,
                    phone = it.phone,
                    onContactClick = { navigateToSecondStep(it.id) }
                )
            }
        }
    }
}

@Composable
private fun Contact(name: String, phone: String, onContactClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onContactClick)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_circle),
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Column {
            Text(text = name)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = phone)
            Divider(thickness = 1.dp, color = Gray)
        }
    }
}