package com.daiatech.chitralekhan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChitraLekhan(
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxSize().then(modifier)) {
        Text("Namaste! Android")
    }
}