package ir.mehdisekoba.banimode.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.screens.theme.Cadet_Grey
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Green

@Composable
fun AppBottomBar(current: AppNavKey, onSelect: (AppNavKey) -> Unit) {
    val items = listOf(
        BottomNavigationItem(HomeKey, R.string.title_home, R.drawable.home, R.drawable.home_bold),
        BottomNavigationItem(
            CategoryKey,
            R.string.title_category,
            R.drawable.category,
            R.drawable.category_bold
        ),
        BottomNavigationItem(
            BasketKey,
            R.string.title_basket,
            R.drawable.basket,
            R.drawable.basket_bold
        ),
        BottomNavigationItem(
            ProfileKey,
            R.string.title_profile,
            R.drawable.user,
            R.drawable.user_bold
        )
    )
    NavigationBar(containerColor = Color.White, tonalElevation = 6.dp) {
        items.forEach { item ->
            val isSelected = current::class == item.key::class
            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelect(item.key) },
                icon = {
                    Icon(
                        painter = painterResource(if (isSelected) item.selectedIconResId else item.iconResId),
                        contentDescription = null
                    )

                },
                label = {
                    Text(
                        text = stringResource(item.titleResId),
                        style = MaterialTheme.typography.titleLarge
                    )
                }, colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Green,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Eerie_Black,
                    unselectedTextColor = Cadet_Grey,
                    indicatorColor = Color.Transparent
                ), alwaysShowLabel = false
            )
        }
    }
}
