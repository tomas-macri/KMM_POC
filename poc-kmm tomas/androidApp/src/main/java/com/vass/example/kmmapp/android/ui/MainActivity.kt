package com.vass.example.kmmapp.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vass.example.kmmapp.android.ui.theme.KMMAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KMMAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val viewModel: MainViewModel = koinViewModel()
                    val inserted: Boolean by viewModel.inserted.observeAsState(false)
                    Greeting("Android", inserted){
                        viewModel.getAnotherQuote()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, inserted: Boolean, onClick: () -> Unit) {
    if (!inserted){
    Text(
        text = "Hello $name!",
        modifier = Modifier.clickable {  onClick() }
    )
    } else{
        Text(
            text = "Hello VAAAAAAAAA $name!",
            modifier = Modifier.clickable {  onClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KMMAppTheme {
        Greeting("Android", false) {}
    }
}