package ir.mehdisekoba.banimode.screens.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.data.model.categoury.ResponseCategory
import ir.mehdisekoba.banimode.screens.theme.Castleton_Green
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Green
import ir.mehdisekoba.banimode.screens.theme.White
import ir.mehdisekoba.banimode.utils.BASE_URL_JSOUP
import ir.mehdisekoba.banimode.utils.LoadImageWithShimmerAndError
import ir.mehdisekoba.banimode.utils.MEGA_SUB_HEADER
import ir.mehdisekoba.banimode.utils.MENU_ITEM_TITLE
import ir.mehdisekoba.banimode.utils.ShowErrorIfNeeded
import ir.mehdisekoba.banimode.utils.SvgImage
import ir.mehdisekoba.banimode.utils.network.NetworkRequest
import ir.mehdisekoba.banimode.viewmodel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onOpenDetails: (String) -> Unit,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val categoryState by viewModel.categoryData.collectAsStateWithLifecycle()
        viewModel.callDetailApi()

        Surface(modifier = modifier.fillMaxSize(), color = White) {
            when (categoryState) {
                is NetworkRequest.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularWavyProgressIndicator(color = Green, trackColor = Castleton_Green)
                    }
                }

                is NetworkRequest.Error -> {
                    ShowErrorIfNeeded(
                        isError = true,
                        errorMessage = categoryState.error.orEmpty(),
                        snackbarHostState = snackbarHostState
                    )
                }

                is NetworkRequest.Success -> {
                    val categoryResponse =
                        (categoryState as NetworkRequest.Success<ResponseCategory>).data
                    val mainCategories = categoryResponse?.data?.firstOrNull()?.childs
                        ?.filterNotNull()
                        .orEmpty()

                    if (mainCategories.isNotEmpty()) {
                        CategorySuccessScreen(
                            mainCategories = mainCategories,
                            onOpenDetails = onOpenDetails
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CategorySuccessScreen(
    mainCategories: List<ResponseCategory.Data.Child>,
    onOpenDetails: (String) -> Unit
) {
    var selectedCategory by remember {
        mutableStateOf(mainCategories.firstOrNull { it.id == 14 } ?: mainCategories.firstOrNull())
    }

    val railCategories = remember(mainCategories) {
        mainCategories.filter { it.type == MEGA_SUB_HEADER }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            modifier = Modifier
                .fillMaxHeight()
                .width(88.dp)
                .background(Color.White),
            containerColor = Color.White,
            contentColor = Eerie_Black,
            windowInsets = WindowInsets(0, 0, 0, 0)
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))

                val useCategories = railCategories.ifEmpty { mainCategories }

                useCategories.forEach { category ->
                    val isSelected = category.id == selectedCategory?.id

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isSelected)
                                    Green.copy(alpha = 0.1f)
                                else Color.Transparent
                            )
                            .clickable { selectedCategory = category },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(48.dp)
                                .background(if (isSelected) Green else Color.Transparent)
                        )

                        NavigationRailItem(
                            selected = isSelected,
                            onClick = { selectedCategory = category },
                            icon = {
                                val url = category.iconUrl?.let {
                                    val base = BASE_URL_JSOUP.trimEnd('/')
                                    val path = it.trimStart('/')
                                    "$base/$path"
                                }

                                SvgImage(
                                    url = url!!,
                                    modifier = Modifier.size(32.dp),
                                    tint = if (isSelected) Green else Eerie_Black
                                )
                            },
                            label = {
                                Text(
                                    text = category.title.orEmpty(),
                                    modifier = Modifier.padding(horizontal = 6.dp),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (isSelected) Green else Eerie_Black,
                                    textAlign = TextAlign.Center,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            alwaysShowLabel = true,
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = Green,
                                selectedTextColor = Green,
                                unselectedIconColor = Eerie_Black,
                                unselectedTextColor = Eerie_Black,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }

        VerticalDivider(
            modifier = Modifier.fillMaxHeight(),
            color = MaterialTheme.colorScheme.outlineVariant
        )

        CategoryContent(
            selectedCategory = selectedCategory,
            onOpenDetails = onOpenDetails,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun CategoryContent(
    selectedCategory: ResponseCategory.Data.Child?,
    onOpenDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (selectedCategory == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.Select_category), style = MaterialTheme.typography.titleLarge)
        }
        return
    }

    val subCategories = remember(selectedCategory) {
        selectedCategory.childs
            ?.filterNotNull()
            ?.filter { it.type == MENU_ITEM_TITLE }
            .orEmpty()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        // Header spanning all columns
        item(span = { GridItemSpan(3) }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${stringResource(R.string.All_goods)} ${selectedCategory.title.orEmpty()}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        itemsIndexed(subCategories) { _, subCategory ->
            SubCategoryItem(
                subCategory = subCategory,
                onOpenDetails = onOpenDetails
            )
        }
    }
}


@Composable
fun SubCategoryItem(
    subCategory: ResponseCategory.Data.Child.Child,
    onOpenDetails: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                subCategory.link?.let { onOpenDetails(it) }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = subCategory.imageUrl?.let { "${BASE_URL_JSOUP}$it" }
        LoadImageWithShimmerAndError(
            imageUrl = imageUrl,
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            shimmerEnabled = true
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subCategory.title.orEmpty(),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
    }
}
