package com.example.mui7.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.mui7.data.TaxPayer


@Composable
fun TaxPayersScreen(
    paddingValues: PaddingValues,
    dataSet1: List<TaxPayer>,
    dataSet2: List<TaxPayer>
) {
    var currentDataSet by remember {
        mutableStateOf(dataSet1)
    }
    var currentSelectedTaxPayer by remember { mutableStateOf<TaxPayer?>(null) }
    val selectedTaxPayers = remember { mutableStateListOf<TaxPayer>() }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Tax Payers",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.W500)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            TabItem(
                onClick = { currentDataSet = dataSet1 },
                header = "Lodz",
                isSelected = currentDataSet == dataSet1
            )
            TabItem(
                onClick =  { currentDataSet = dataSet2 },
                header = "Warsaw",
                isSelected = currentDataSet == dataSet2
            )
            AnimatedVisibility(visible = selectedTaxPayers.isNotEmpty()) {
                TabItem(
                    header = "Selected",
                    isSelected = selectedTaxPayers == currentDataSet,
                    onClick =  {currentDataSet = selectedTaxPayers })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TaxPayerList(dataSet = currentDataSet, onContactSelected = { currentSelectedTaxPayer = it })
        if (currentSelectedTaxPayer != null && currentDataSet != selectedTaxPayers) {
            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.65f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        AnimatedVisibility(visible = currentSelectedTaxPayer != null && currentDataSet != selectedTaxPayers) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TaxPayerDetails(currentSelectedTaxPayer!!)
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { selectedTaxPayers.add(currentSelectedTaxPayer!!) }) {
                    Text("Select")
                }
            }
        }
    }
}

@Composable
fun TaxPayerDetails(taxPayer: TaxPayer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = taxPayer.instanceName,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Address : " + taxPayer.address,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Owner : " + taxPayer.owner.name + " " + taxPayer.owner.surname,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Composable
fun TabItem(
    header: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val underlineWidth by animateDpAsState(
        targetValue = if (isSelected) 40.dp else 0.dp,
        label = "UnderlineWidth"
    )
    val textColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
        label = "TextColor"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = header,
            color = textColor,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(underlineWidth)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(1.dp))
        )
    }
}

