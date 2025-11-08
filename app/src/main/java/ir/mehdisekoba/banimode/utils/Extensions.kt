package ir.mehdisekoba.banimode.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.svg.SvgDecoder
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.crossfade.CrossfadePlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import ir.mehdisekoba.banimode.screens.theme.Green
import ir.mehdisekoba.banimode.screens.theme.White
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import java.text.DecimalFormat


fun Int.moneySeparating(): String {
    return "${DecimalFormat("#,###.##").format(this)} تومان"
}

fun Int.moneySeparatingWithoutToman(): String {
    return DecimalFormat("#,###.##").format(this)
}
suspend fun SnackbarHostState.showError(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration.() -> SnackbarDuration = { this }
) {
    this.showSnackbar(message = message, actionLabel = actionLabel, duration = duration(SnackbarDuration.Short))
}
@Composable
fun ShowErrorIfNeeded(
    isError: Boolean,
    errorMessage: String?,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = isError, key2 = errorMessage) {
        if (isError && !errorMessage.isNullOrEmpty()) {
            scope.launch {
                snackbarHostState.showError(errorMessage)
            }
        }
    }
}
@Composable
fun LoadImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    shimmerEnabled: Boolean = true,
    crossfadeDuration: Int = 500
) {
    CoilImage(
        imageModel = { url },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = alignment
        ),
        component = rememberImageComponent {
            if(shimmerEnabled) {
                +ShimmerPlugin(
                    shimmer = Shimmer.Flash(
                        baseColor = Color.LightGray,
                        highlightColor = Color.White
                    )
                )
            }
            +CrossfadePlugin(
                duration = crossfadeDuration,
            )
        },
        modifier = modifier.semantics { this.contentDescription = contentDescription ?: "" },
    )
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LoadImageWithShimmerAndError(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    cornerRadiusDp: Int = 0,
    shimmerEnabled: Boolean = true,
    errorContent: @Composable (() -> Unit)? = null
) {
    CoilImage(
        imageModel = { imageUrl },
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadiusDp.dp))
            .semantics { this.contentDescription = contentDescription ?: "" },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = alignment
        ),
        loading = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                if (shimmerEnabled) {

                    CircularWavyProgressIndicator(color = Green)
                } else {
                    CircularWavyProgressIndicator(color = Green)
                }
            }
        },
        failure = {
            if (errorContent != null) {
                errorContent()
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                }
            }
        },
        component = rememberImageComponent {
            if (shimmerEnabled) {
                +ShimmerPlugin(
                    Shimmer.Flash(
                        baseColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                        highlightColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
                    )
                )
            }
            +CrossfadePlugin()
        }
    )
}


@Composable
fun rememberSvgImageLoader(context: Context): ImageLoader {
    return remember {
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}
@Composable
fun SvgImage(
    url: String,
    modifier: Modifier = Modifier,
    tint: Color? = null
) {
    val context = LocalContext.current
    val density = LocalDensity.current.density

    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .crossfade(true)
            .build()
    }

    val request = ImageRequest.Builder(context)
        .data(url)
        .size((40 * density).toInt(), (40 * density).toInt())
        .build()

    val painter = rememberAsyncImagePainter(
        model = request,
        imageLoader = imageLoader
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        colorFilter = tint?.let { ColorFilter.tint(it) },
        contentScale = ContentScale.Crop
    )
}






