package com.extaleusinc.screening.base.projectsSurf

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.extaleusinc.screening.R
import com.extaleusinc.screening.base.mainScreen.ProjectInfo
import com.extaleusinc.screening.base.uiHelpers.CustomAsyncImage

@Composable
fun ProjectsSurfScreen(items: List<ProjectInfo>, onClick: (ProjectInfo) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F7FE)) // напрямую из дизайна, по нормальному использовал бы материалы
            .padding(8.dp)
    ) {
        items(items) { item ->
            ItemCard(cardInfo = item) {
                onClick(it)
            }
        }
    }
}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier, cardInfo: ProjectInfo, onClick: (ProjectInfo) -> Unit
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { onClick(cardInfo) }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.fillMaxWidth()) { // Почему BOX внутри Card, а не просто Column? Потому что текст не под иконкой, а немного на неё заходит по высоте.
                CustomAsyncImage( // Просто как пример для динамических изображений, с кешированием и индикатором загрузки
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopEnd),
                    cardInfo.companyImg
                )
                Text(
                    text = cardInfo.projectName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = modifier
                        .padding(top = 25.dp)
                        .width(100.dp)
                )
            }

            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                when (cardInfo.users.size) {
                    0 -> {
                        Text(
                            text = stringResource(R.string.team_not_collect),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            maxLines = 1
                        )
                    }

                    1, 2, 3 -> {
                        cardInfo.users.forEachIndexed { index, item ->
                            CustomAsyncImage(
                                modifier = Modifier
                                    .size(32.dp)
                                    .offset(x = (-8 * index).dp)
                                    .border(
                                        border = BorderStroke(2.dp, color = Color.White),
                                        shape = CircleShape
                                    ), item.profileImg
                            )
                        }
                    }

                    else -> {
                        Row() {
                            cardInfo.users.take(3).forEachIndexed { index, item ->
                                CustomAsyncImage(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .offset(x = (-8 * index).dp)
                                        .border(
                                            border = BorderStroke(2.dp, color = Color.White),
                                            shape = CircleShape
                                        ), item.profileImg
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .offset(x = (-8 * 3).dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                                    .border(
                                        border = BorderStroke(2.dp, color = Color.White),
                                        shape = CircleShape
                                    )
                            ) {
                                Text(
                                    text = "+${cardInfo.users.size - 3}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
