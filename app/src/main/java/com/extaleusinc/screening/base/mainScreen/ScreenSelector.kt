package com.extaleusinc.screening.base.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ScreenSelector(
    modifier: Modifier,
    options: List<String>,
    selectedIndex: Int,
    underlineError: Boolean = false,
    onOptionSelected: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        options.forEachIndexed { index, text ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable { onOptionSelected(index) }, // Можно перенести .clickable на Text() ниже, что бы не было анимациии нажатия вне текста.
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (index == selectedIndex) {
                        Color(0xFF4BC0FF)
                    } else {
                        Color.Black.copy(alpha = 0.3f)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(3.dp)
                        .clip(RoundedCornerShape(8.dp, 8.dp, 0.dp, 0.dp))
                        .background(
                            color = if (index == selectedIndex) {
                                if (underlineError) {
                                    Color(0xFF8E5BFD)
                                } else {
                                    Color(0xFF4BC0FF)
                                }
                            } else {
                                Color.Transparent
                            }
                        )
                ) { }
            }
        }
    }
}