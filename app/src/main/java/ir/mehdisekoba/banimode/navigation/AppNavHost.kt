package ir.mehdisekoba.banimode.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import ir.mehdisekoba.banimode.screens.basket.BasketScreen
import ir.mehdisekoba.banimode.screens.category.CategoryScreen
import ir.mehdisekoba.banimode.screens.details.DetailsScreen
import ir.mehdisekoba.banimode.screens.home.HomeScreen
import ir.mehdisekoba.banimode.screens.profile.ProfileScreen
import ir.mehdisekoba.banimode.screens.splash.SplashScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,onFinish: () -> Unit) {
    // create a saveable back stack with start = Splash
    val backStack = rememberNavBackStack(SplashKey)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            // Splash
            entry<SplashKey> { _ ->
                SplashScreen(onTimeout = {

                    backStack.removeLastOrNull()
                    backStack.add(HomeKey)
                })
            }
            entry<HomeKey> { _ ->
                Scaffold(
                    bottomBar = {
                        AppBottomBar(
                            current = HomeKey,
                            onSelect = { target ->
                                if (backStack.lastOrNull() != target) {

                                    backStack.removeLastOrNull()
                                    backStack.add(target)
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        onOpenDetails = { id -> backStack.add(DetailsKey(id)) },
                        onOpenCategory = { backStack.add(CategoryKey) }
                    )
                }
            }

            // Category
            entry<CategoryKey> { _ ->
                Scaffold(
                    bottomBar = {
                        AppBottomBar(current = CategoryKey, onSelect = { target ->
                            if (backStack.lastOrNull() != target) {
                                backStack.removeLastOrNull()
                                backStack.add(target)
                            }
                        })
                    }
                ) { innerPadding ->
                    CategoryScreen(modifier = Modifier.padding(innerPadding), onOpenDetails = { id -> backStack.add(DetailsKey(id)) })
                }
            }

            // Basket
            entry<BasketKey> { _ ->
                Scaffold(
                    bottomBar = {
                        AppBottomBar(current = BasketKey, onSelect = { target ->
                            if (backStack.lastOrNull() != target) {
                                backStack.removeLastOrNull()
                                backStack.add(target)
                            }
                        })
                    }
                ) { innerPadding ->
                    BasketScreen(modifier = Modifier.padding(innerPadding))
                }
            }

            // Profile
            entry<ProfileKey> { _ ->
                Scaffold(
                    bottomBar = {
                        AppBottomBar(current = ProfileKey, onSelect = { target ->
                            if (backStack.lastOrNull() != target) {
                                backStack.removeLastOrNull()
                                backStack.add(target)
                            }
                        })
                    }
                ) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }

            entry<DetailsKey> { key ->
                DetailsScreen(itemId = key.productId, onBack = { backStack.removeLastOrNull() })
            }
        },
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            } else {
                onFinish()
            }
        }
    )
}