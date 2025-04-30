package com.example.mui7.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mui7.data.lodzTaxPayersSet1
import com.example.mui7.data.warsawTaxPayersSet1
import com.example.mui7.presentation.theme.MUI7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MUI7Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaxPayersScreen(
                        paddingValues = innerPadding,
                        dataSet1 = lodzTaxPayersSet1,
                        dataSet2 = warsawTaxPayersSet1
                    )
                }
            }
        }
    }
}
