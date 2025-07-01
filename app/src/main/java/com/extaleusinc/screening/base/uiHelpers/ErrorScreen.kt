package com.extaleusinc.screening.base.uiHelpers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.extaleusinc.screening.R

@Composable
fun ErrorScreen(modifier: Modifier, onClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomAsyncImage(
                modifier = Modifier.size(140.dp),
                url = "https://thumbs.dreamstime.com/b/oh-keine-sprechblase-kein-text-handgeschriebenes-angebot-phrase-vektorgrafik-vektor-darstellung-f%C3%BCr-den-druck-auf-t-shirt-268274055.jpg"
            )
            Text(
                text = stringResource(R.string.something_went_wrong),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W800),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                text = stringResource(R.string.please_repeat),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            OutlinedButton(
                onClick = onClick,
                border = BorderStroke(1.dp, Color(0xFF4BC0FF)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.refresh),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600),
                    color = Color(0xFF4BC0FF),
                )
            }
        }
    }
}