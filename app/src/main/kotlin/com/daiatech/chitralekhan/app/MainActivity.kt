/**
 * Copyright (c) 2024 DAIA Tech Pvt Ltd. All rights reserved.
 */

package com.daiatech.chitralekhan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.daiatech.chitralekhan.ChitraLekhan
import com.daiatech.chitralekhan.app.theme.ChitraLekhanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChitraLekhanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChitraLekhan(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
