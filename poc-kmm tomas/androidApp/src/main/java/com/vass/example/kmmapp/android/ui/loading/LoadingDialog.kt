package com.vass.example.kmmapp.android.ui.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingDialog(){
    Dialog(onDismissRequest = {}, properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {

        Box(modifier = Modifier
            .padding(24.dp)
            .background(Color.Black, RoundedCornerShape(5.dp))
            .fillMaxWidth()
            .border( width = 3.dp,
                color = Color.White,
                shape = RoundedCornerShape(5.dp)
            )
            , contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)) {
                Text(text = "LOADING...", modifier = Modifier.padding(16.dp).fillMaxWidth(), color = Color.White, textAlign = TextAlign.Center)
                CircularProgressIndicator(modifier = Modifier.width(48.dp).fillMaxWidth().align(Alignment.CenterHorizontally), color = Color.White)
            }

        }

    }
}

@Preview(device = Devices.PIXEL_4, showSystemUi = true, showBackground = true)
@Composable
fun LoadingPreview(){
    LoadingDialog()
}