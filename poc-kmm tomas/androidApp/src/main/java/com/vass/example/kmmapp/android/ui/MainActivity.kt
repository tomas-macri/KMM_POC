package com.vass.example.kmmapp.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.vass.example.kmmapp.android.ui.theme.KMMAppTheme
import com.vass.example.kmmapp.data.model.Quote
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KMMAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val viewModel: MainViewModel = koinViewModel()
                    val mainUiState: MainUiState by viewModel.mainUiState.observeAsState(MainUiState())
                    InitialQuotesFetch(LocalLifecycleOwner.current) { viewModel.getAllQuotes() }
                    MainScreen(mainUiState, onClickAddQuote = { viewModel.getAnotherQuote() }, onClickResetQuotes = { viewModel.resetQuotes() })
                }
            }
        }
    }
}

@Composable
private fun MainScreen(mainUiState: MainUiState, onClickAddQuote: () -> Unit, onClickResetQuotes: () -> Unit) {
    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
        if (!mainUiState.loading) {
            if (mainUiState.quotes.isNotEmpty()) {
                QuotesList(mainUiState.quotes)
            } else {
                Box(modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .padding(16.dp), contentAlignment = Alignment.Center) {
                    Text(text = "There are no quotes yet, add one with the button below", textAlign = TextAlign.Center)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.SpaceAround) {
                Button(onClick = { onClickAddQuote() }) {
                    Text(text = "Get another!")
                }
                Button(onClick = { onClickResetQuotes() }) {
                    Text(text = "Clear quotes :(")
                }
            }
        }
    }
}

@Composable
fun QuotesList(quotes: List<Quote>) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.9f)) {
        items(quotes.size) {
            val quoteItem = quotes[it]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(textAlign = TextAlign.Justify, text = "\"${quoteItem.quote}\"")
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(modifier = Modifier.align(Alignment.End), text = quoteItem.author)
                }
            }
        }


    }

}

@Composable
fun InitialQuotesFetch(lifecycleOwner: LifecycleOwner, onEntered: () -> Unit) {
    DisposableEffect(key1 = Unit) {
        val observer = LifecycleEventObserver { _, _ -> onEntered() }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { //Main difference with normal LaunchedEffect is this function to release resources
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun QuotesPreview() {
    KMMAppTheme {
        val quotes = listOf(
            Quote(1, "This is my first quote", "Walter White"),
            Quote(2, "This is my second quote and is a loooooooooooong one really", "Jesse Pinkman"),
            Quote(
                3,
                "This is my third quote and is a loooooooooooong one really. Lorem ipsum Lorem ipsumLorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum",
                "Saul Goodman"
            ),
        )
        MainScreen(MainUiState(quotes), {}, {})
    }
}