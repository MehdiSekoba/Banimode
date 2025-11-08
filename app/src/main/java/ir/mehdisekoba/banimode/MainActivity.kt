package ir.mehdisekoba.banimode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import dagger.hilt.android.AndroidEntryPoint
import ir.mehdisekoba.banimode.navigation.AppNavHost
import ir.mehdisekoba.banimode.screens.theme.BanimodeTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanimodeTheme(darkTheme = false, dynamicColor = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White
                ) { innerPadding ->
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        AppNavHost(modifier = Modifier.padding(innerPadding), onFinish = {
                            finish()
                        })
                    }
                }
            }
        }
    }
}
