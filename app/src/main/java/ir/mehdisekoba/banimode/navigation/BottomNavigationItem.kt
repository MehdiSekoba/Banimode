package ir.mehdisekoba.banimode.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavigationItem(
    val key: AppNavKey,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
    @DrawableRes val selectedIconResId: Int
)
