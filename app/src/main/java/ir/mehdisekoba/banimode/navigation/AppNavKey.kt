package ir.mehdisekoba.banimode.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface AppNavKey : NavKey

@Serializable
data object SplashKey : AppNavKey

@Serializable
data object HomeKey : AppNavKey

@Serializable
data object CategoryKey : AppNavKey

@Serializable
data object BasketKey : AppNavKey

@Serializable
data object ProfileKey : AppNavKey

@Serializable
data class DetailsKey(val productId: String) : AppNavKey