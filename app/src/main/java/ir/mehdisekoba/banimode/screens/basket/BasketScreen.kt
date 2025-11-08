package ir.mehdisekoba.banimode.screens.basket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black

@Composable
fun BasketScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.cart_empty)
            )
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = 6,
                reverseOnRepeat = true,

            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.fillMaxWidth().height(180.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(R.string.empty_basket),
                style = MaterialTheme.typography.titleLarge,
                color = Eerie_Black
            )
        }
    }
}