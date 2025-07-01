package com.extaleusinc.screening.base.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.extaleusinc.screening.R
import com.extaleusinc.screening.base.uiHelpers.CustomAsyncImage

@Composable
fun ProfilePart(modifier: Modifier, name: String, imgUrl: String, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.hello),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = "$name \uD83D\uDC4B",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W800,
                    fontSize = 30.sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        CustomAsyncImage(
            modifier = Modifier
                .size(55.dp)
                .clickable { onClick() },
            imgUrl
        )
    }
}