package ir.mehdisekoba.banimode.screens.splash


import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.jordond.connectivity.Connectivity
import dev.jordond.connectivity.compose.rememberConnectivityState
import ir.mehdisekoba.banimode.BuildConfig
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.screens.theme.AuraMetalMaurus
import ir.mehdisekoba.banimode.screens.theme.Castleton_Green
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Green
import ir.mehdisekoba.banimode.screens.theme.Spanish_Crimson
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    onTimeout: () -> Unit
) {
    val state = rememberConnectivityState {
        autoStart = true
    }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    when (state.status) {
        is Connectivity.Status.Connected -> {

            SplashLottie {
                LaunchedEffect(Unit) {
                    delay(3500)
                    onTimeout()
                }
            }


        }

        is Connectivity.Status.Disconnected -> {
            showBottomSheet = true
        }

        else -> {}
    }



    if (showBottomSheet) {
        RetrySheet(
            onRetry = { },
            onDismiss = {},
            sheetState = sheetState
        )
    }
}


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun SplashLottie(onFinished: @Composable () -> Unit) {

    onFinished()
    //load animation
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.optimized_lottie)
        )
        val progress by animateLottieCompositionAsState(
            composition,
            iterations = 6,
            reverseOnRepeat = true,
            speed = 2.5f

        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxWidth().height(180.dp)
        )

        Spacer(modifier = Modifier.height(68.dp))
        CircularWavyProgressIndicator(color = Green, trackColor = Castleton_Green)
        Spacer (modifier = Modifier.height(16.dp))
        Text(
            text = "${stringResource(R.string.app_version)} : ${BuildConfig.VERSION_NAME}",
            color = Eerie_Black,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RetrySheet(
    onRetry: () -> Unit,
    onDismiss: () -> Unit,
    sheetState: SheetState
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = AuraMetalMaurus,
        scrimColor = Color.Transparent,
        dragHandle = { null },

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.signal_tower)
            )
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = 6,
                reverseOnRepeat = true
            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = stringResource(R.string.no_internet_connection),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.check_your_internet_connection_and_try_again),
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(containerColor = Spanish_Crimson)
            ) {
                Text(
                    text = stringResource(R.string.retry),
                    color = Color.White
                )
            }
        }
    }
}
