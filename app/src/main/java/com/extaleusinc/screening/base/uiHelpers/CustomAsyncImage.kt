package com.extaleusinc.screening.base.uiHelpers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import coil.util.DebugLogger
import com.extaleusinc.screening.R

@Composable
fun CustomAsyncImage(
    modifier: Modifier = Modifier,
    url: String?,
    contentScale: ContentScale = ContentScale.Crop,
    shape: Shape = CircleShape,
    progressIndSize: Dp = 28.dp,
    contentDescription: String? = null,
    errorResId: Int = R.color.black
) {
    val context = LocalContext.current
    val imageLoader = context.imageLoader.newBuilder().logger(DebugLogger()).build()
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context).data(url)
            .addHeader(stringResource(R.string.accept_caps), stringResource(R.string.jpeg_mime_type)) // Лучше использовать константы
            .error(errorResId).crossfade(true).build(), imageLoader = imageLoader
    )

    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .matchParentSize()
                .clip(shape = shape),
            contentScale = contentScale,
        )

        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(progressIndSize),
                    strokeWidth = 3.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            is AsyncImagePainter.State.Error -> {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.error
                )
            }

            else -> {}
        }
    }
}