package ir.mehdisekoba.banimode.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.revenuecat.placeholder.PlaceholderDefaults
import com.revenuecat.placeholder.placeholder
import dev.jordond.connectivity.Connectivity
import dev.jordond.connectivity.compose.rememberConnectivityState
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.data.model.categoury.ResponseCategory
import ir.mehdisekoba.banimode.data.model.home.AmazingUiModel
import ir.mehdisekoba.banimode.data.model.home.MainMenuModel
import ir.mehdisekoba.banimode.data.model.home.ResponseAmazing
import ir.mehdisekoba.banimode.data.model.home.ResponseBrandHome
import ir.mehdisekoba.banimode.data.model.home.ResponseProducts
import ir.mehdisekoba.banimode.screens.theme.AmazingSectionBackground
import ir.mehdisekoba.banimode.screens.theme.BestSellerBackground
import ir.mehdisekoba.banimode.screens.theme.Bright_Gray
import ir.mehdisekoba.banimode.screens.theme.Cadet_Grey
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Green
import ir.mehdisekoba.banimode.screens.theme.Spanish_Crimson
import ir.mehdisekoba.banimode.screens.theme.White
import ir.mehdisekoba.banimode.utils.BASE_URL_JSOUP
import ir.mehdisekoba.banimode.utils.LoadImage
import ir.mehdisekoba.banimode.utils.MENU_ITEM_TITLE
import ir.mehdisekoba.banimode.utils.ProductsCategories
import ir.mehdisekoba.banimode.utils.ShowErrorIfNeeded
import ir.mehdisekoba.banimode.utils.VIEW_ALL_CARD
import ir.mehdisekoba.banimode.utils.WITHOUT_DAYS
import ir.mehdisekoba.banimode.utils.WITH_DAYS
import ir.mehdisekoba.banimode.utils.moneySeparating
import ir.mehdisekoba.banimode.utils.network.NetworkRequest
import ir.mehdisekoba.banimode.utils.shimmerLoading
import ir.mehdisekoba.banimode.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.TimeUnit

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onOpenDetails: (String) -> Unit,
    onOpenCategory: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val connectivityState = rememberConnectivityState {
        autoStart = true
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val banners by viewModel.banners.collectAsStateWithLifecycle()
    val category by viewModel.categoryHome.collectAsStateWithLifecycle()
    val amazing by viewModel.amazingData.collectAsStateWithLifecycle()
    val brand by viewModel.brandData.collectAsStateWithLifecycle()
    val products by viewModel.bestSeller.collectAsStateWithLifecycle()
    val isConnected = connectivityState.status is Connectivity.Status.Connected

    var selectedProductCategory by remember {
        mutableStateOf(ProductsCategories.FASHION)
    }

    LaunchedEffect(isConnected) {
        viewModel.loadBanners()
        viewModel.loadingCategoryHome()
        viewModel.loadingAmazingData()
        viewModel.loadingBrandHome()
        viewModel.getProductsData(selectedProductCategory)
    }
    LaunchedEffect(selectedProductCategory) {
        if (isConnected) {
            viewModel.getProductsData(selectedProductCategory)
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                scrollBehavior = scrollBehavior,
                containerColor = Color.White
            )
        },
        contentColor = Color.White
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(
                bottom = WindowInsets.navigationBars.asPaddingValues()
                    .calculateBottomPadding() + 36.dp
            )
        ) {

            item {
                BannerStateWrapper(
                    bannersState = banners,
                    snackbarHostState = snackbarHostState
                )
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }

            item {
                CategoryStateWrapper(
                    categoryState = category,
                    snackbarHostState = snackbarHostState
                )
            }

            item {
                AmazingStateWrapper(
                    amazingState = amazing,
                    snackbarHostState = snackbarHostState,
                    onItemClick = onOpenDetails
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                BrandStateWrapper(
                    brandState = brand,
                    snackbarHostState = snackbarHostState,
                    onBrandClick = onOpenDetails
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                ProductWrapper(
                    productState = products,
                    snackbarHostState = snackbarHostState,
                    selectedProductCategory = selectedProductCategory,
                    onCategorySelected = { newCategory -> selectedProductCategory = newCategory },
                    onItemClick = onOpenDetails
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior, containerColor: Color) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(42.dp)
                        .widthIn(min = 42.dp, max = 120.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Row {
                    IconButton(onClick = { }) {
                        Icon(painterResource(R.drawable.search), contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(painterResource(R.drawable.heart), contentDescription = null)
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = containerColor)
    )
}

@Composable
private fun BannerStateWrapper(
    bannersState: NetworkRequest<List<String>>,
    snackbarHostState: SnackbarHostState
) {
    when (bannersState) {
        is NetworkRequest.Loading -> {
            CarouselBanner(banners = emptyList(), isLoading = true)
        }

        is NetworkRequest.Success -> {
            CarouselBanner(banners = bannersState.data ?: emptyList(), isLoading = false)
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(
                isError = true,
                errorMessage = bannersState.error ?: "",
                snackbarHostState = snackbarHostState
            )
        }
    }
}

@Composable
private fun CategoryStateWrapper(
    categoryState: NetworkRequest<ResponseCategory>,
    snackbarHostState: SnackbarHostState
) {
    val menus = remember { mutableStateListOf<MainMenuModel>() }

    when (categoryState) {
        is NetworkRequest.Loading -> {
            CategoryMenuRow(
                menus = emptyList(),
                isLoading = true
            )
        }

        is NetworkRequest.Success -> {
            LaunchedEffect(categoryState) {
                menus.clear()
                menus.addAll(extractMainMenus(categoryState))
            }
            CategoryMenuRow(menus = menus, isLoading = false)
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(
                isError = true,
                errorMessage = categoryState.error ?: "",
                snackbarHostState = snackbarHostState
            )
            CategoryMenuRow(menus = emptyList(), isLoading = true)
        }
    }
}

@Composable
private fun AmazingStateWrapper(
    amazingState: NetworkRequest<ResponseAmazing>,
    snackbarHostState: SnackbarHostState,
    onItemClick: (String) -> Unit
) {
    when (amazingState) {
        is NetworkRequest.Loading -> {
            LoadAmazingContent(
                isLoading = true,
                cardWidth = 140.dp,
                cardHeight = 220.dp
            )
        }

        is NetworkRequest.Success -> {
            amazingState.data?.data?.let { data ->
                val uiList = mapResponseAmazingToUiModels(
                    data = data.data,
                    timer = data.timer
                )
                LoadAmazingContent(
                    isLoading = false,
                    data = uiList,
                    remainingTimeMillis = data.timer,
                    onItemClick = onItemClick
                )
            }
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(
                isError = true,
                errorMessage = amazingState.error ?: "",
                snackbarHostState = snackbarHostState
            )
            LoadAmazingContent(
                isLoading = true,
                cardWidth = 140.dp,
                cardHeight = 220.dp
            )
        }
    }
}

@Composable
private fun BrandStateWrapper(
    brandState: NetworkRequest<ResponseBrandHome>,
    snackbarHostState: SnackbarHostState,
    onBrandClick: (String) -> Unit
) {
    when (brandState) {
        is NetworkRequest.Loading -> {
            BrandHomeRow(
                data = null,
                isLoading = true
            )
        }

        is NetworkRequest.Success -> {
            brandState.data?.data?.take(10)?.filterNotNull()?.let { data ->
                BrandHomeRow(
                    data = data,
                    isLoading = false,
                    onBrandClick = onBrandClick
                )
            }
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(
                isError = true,
                errorMessage = brandState.error ?: "",
                snackbarHostState = snackbarHostState
            )
            BrandHomeRow(
                data = null,
                isLoading = true
            )
        }
    }
}

@Composable
private fun ProductWrapper(
    productState: NetworkRequest<ResponseProducts>,
    snackbarHostState: SnackbarHostState,
    selectedProductCategory: ProductsCategories,
    onCategorySelected: (ProductsCategories) -> Unit,
    onItemClick: (String) -> Unit
) {
    when (productState) {
        is NetworkRequest.Loading -> {
            LoadProductContent(
                isLoading = true,
                cardWidth = 140.dp,
                cardHeight = 220.dp
            )
        }

        is NetworkRequest.Success -> {
            productState.data?.data?.data?.let { productsList ->
                LoadProductContent(
                    isLoading = false,
                    data = productsList,
                    selectedCategory = selectedProductCategory,
                    onCategorySelected = onCategorySelected,
                    onItemClick = onItemClick
                )
            }
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(
                isError = true,
                errorMessage = productState.error ?: "",
                snackbarHostState = snackbarHostState
            )
            LoadProductContent(
                isLoading = true,
                cardWidth = 140.dp,
                cardHeight = 220.dp
            )
        }
    }
}



@Composable
private fun ProductCategoriesTabs(
    selectedCategory: ProductsCategories,
    onCategorySelected: (ProductsCategories) -> Unit,
    modifier: Modifier = Modifier
) {
    val categories = ProductsCategories.entries.toTypedArray()

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            FilterChip(
                selected = (category == selectedCategory),
                onClick = { onCategorySelected(category) },
                label = {
                    Text(
                        getPersianCategoryName(category),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                border = FilterChipDefaults.filterChipBorder(enabled = false, selected = false),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Green,
                    selectedLabelColor = Bright_Gray,
                    disabledSelectedContainerColor = White,
                    disabledLabelColor = Cadet_Grey
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CarouselBanner(
    banners: List<String>,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    slideIntervalMillis: Long = 3000
) {
    val state = rememberCarouselState(
        initialItem = banners.lastIndex.coerceAtLeast(0),
        itemCount = { banners.size.coerceAtLeast(1) }
    )
    val animationScope = rememberCoroutineScope()

    if (!isLoading) {
        LaunchedEffect(banners) {
            while (true) {
                delay(slideIntervalMillis)
                val nextIndex =
                    if (state.currentItem < banners.lastIndex) state.currentItem + 1 else 0
                animationScope.launch { state.animateScrollToItem(nextIndex) }
            }
        }
    }

    HorizontalCenteredHeroCarousel(
        state = state,
        modifier = modifier
            .shimmerLoading(14.dp, isLoading)
            .fillMaxWidth()
            .height(221.dp)
            .padding(horizontal = 8.dp),
        itemSpacing = 8.dp,
        userScrollEnabled = false,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { index ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
                    .height(205.dp)
                    .shimmerLoading(14.dp, true)
                    .maskClip(MaterialTheme.shapes.extraLarge)
            )
        } else {
            LoadImage(
                url = banners[index],
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
                    .maskClip(MaterialTheme.shapes.extraLarge)
                    .clickable(
                        enabled = true,
                        onClickLabel = "Tap to focus",
                        role = Role.Image
                    ) {
                        animationScope.launch { state.animateScrollToItem(index) }
                    }
            )
        }
    }
}


@Composable
fun CategoryMenuRow(
    menus: List<MainMenuModel>,
    isLoading: Boolean = false,
    onShowAllClicked: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .placeholder(
                                enabled = true,
                                shape = CircleShape,
                                highlight = PlaceholderDefaults.shimmer
                            )
                    ) {}
                } else {
                    Icon(
                        painter = painterResource(R.drawable.category_bold),
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = Eerie_Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .width(120.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    ) {}
                } else {
                    Text(
                        text = stringResource(R.string.title_category),
                        style = MaterialTheme.typography.titleLarge,
                        color = Eerie_Black
                    )
                }
            }
            TextButton(
                onClick = { if (!isLoading) onShowAllClicked?.invoke() }
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(20.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    ) {}
                } else {
                    Text(
                        text = stringResource(R.string.title_see_all),
                        style = MaterialTheme.typography.titleLarge,
                        color = Green
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 8.dp),
        ) {
            if (isLoading) {
                repeat(6) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .width(90.dp)
                    ) {
                        Card(
                            modifier = Modifier.size(80.dp),
                            shape = RoundedCornerShape(18.dp),
                            elevation = CardDefaults.cardElevation(1.dp),
                            colors = CardDefaults.cardColors(containerColor = White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .placeholder(
                                            enabled = true,
                                            highlight = PlaceholderDefaults.shimmer
                                        )
                                ) {}
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .height(18.dp)
                                .fillMaxWidth(0.8f)
                                .placeholder(
                                    enabled = true,
                                    shape = RoundedCornerShape(4.dp),
                                    highlight = PlaceholderDefaults.shimmer
                                )
                        ) {}
                    }
                }
            } else {
                menus.forEach { menu ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .width(90.dp)
                    ) {
                        Card(
                            modifier = Modifier.size(80.dp),
                            shape = RoundedCornerShape(18.dp),
                            elevation = CardDefaults.cardElevation(2.dp),
                            colors = CardDefaults.cardColors(containerColor = White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadImage(
                                    url = menu.imageUrl ?: "",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                            }
                        }
                        Text(
                            text = menu.title,
                            modifier = Modifier.padding(top = 6.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = Eerie_Black,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun LoadAmazingContent(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    data: List<AmazingUiModel>? = null,
    remainingTimeMillis: Int? = null,
    cardWidth: Dp = 140.dp,
    cardHeight: Dp = 200.dp,
    onViewAllClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(184.dp)
                .background(AmazingSectionBackground, shape = RoundedCornerShape(16.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .size(60.dp, 20.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(8.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                } else {
                    remainingTimeMillis?.let {
                        val endTime = System.currentTimeMillis() + it.toLong()
                        CountdownTimerView(
                            endMillis = endTime,
                            modifier = Modifier
                        )
                    }
                }
            }

            Row(
                Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .placeholder(
                                enabled = true,
                                shape = CircleShape,
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .width(120.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.discount),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.title_amazing),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isLoading) {
                items(11) {
                    AmazingTicketCard(
                        item = null,
                        isLoading = true,
                        cardWidth = cardWidth,
                        cardHeight = cardHeight,
                        onClick = {}
                    )
                }
            } else {
                data?.take(11)?.forEach { item ->
                    item {
                        AmazingTicketCard(
                            item = item,
                            isLoading = false,
                            cardWidth = cardWidth,
                            cardHeight = cardHeight,
                            onClick = { onItemClick(item.id.toString()) }
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .width(cardWidth)
                            .height(cardHeight)
                            .background(White, shape = RoundedCornerShape(12.dp))
                            .clickable { onViewAllClick() }
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.title_see_all),
                                color = Color.Black,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(R.drawable.arrow_circle_left),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun AmazingTicketCard(
    item: AmazingUiModel? = null,
    isLoading: Boolean,
    cardWidth: Dp = 140.dp,
    cardHeight: Dp = 200.dp,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .clickable(enabled = !isLoading, onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.45f)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(12.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                } else {
                    item?.let {
                        LoadImage(
                            url = it.image,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                val discount = item?.discountPercent ?: 0
                if (!isLoading && discount > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp)
                            .background(
                                Spanish_Crimson,
                                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "$discount${stringResource(R.string.title_percentage)}",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .align(Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Gray.copy(alpha = 1f))
                            )
                        )
                ) {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(width = 80.dp, height = 16.dp)
                                .placeholder(
                                    enabled = true,
                                    shape = RoundedCornerShape(4.dp),
                                    highlight = PlaceholderDefaults.shimmer
                                )
                                .align(Alignment.CenterStart)
                        )
                    } else {
                        item?.takeIf { it.remainingQty > 0 }?.let {
                            Text(
                                text = "${stringResource(R.string.title_remaining)} ${it.remainingQty}",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .align(Alignment.CenterStart)
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.55f)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.Start
            ) {

                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                } else {
                    item?.let {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleMedium.copy(color = Eerie_Black),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(14.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                    Spacer(modifier = Modifier.height(1.dp))
                } else {
                    item?.takeIf { it.discountPercent > 0 }?.let {
                        Text(
                            text = it.oldPrice.moneySeparating(),
                            color = Spanish_Crimson,
                            style = MaterialTheme.typography.labelSmall.copy(
                                textDecoration = TextDecoration.LineThrough
                            ),
                            maxLines = 1,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                }

                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(20.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    )
                } else {
                    item?.let {
                        Text(
                            text = it.newPrice.moneySeparating(),
                            color = Eerie_Black,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun CountdownTimerView(
    endMillis: Long,
    modifier: Modifier = Modifier,
    onFinish: () -> Unit = {}
) {
    var remainingTime by remember { mutableLongStateOf(endMillis - System.currentTimeMillis()) }

    LaunchedEffect(endMillis) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime = endMillis - System.currentTimeMillis()
        }
        onFinish()
    }

    var timer = remainingTime.coerceAtLeast(0L)
    val days = TimeUnit.MILLISECONDS.toDays(timer)
    timer -= TimeUnit.DAYS.toMillis(days)
    val hours = TimeUnit.MILLISECONDS.toHours(timer)
    timer -= TimeUnit.HOURS.toMillis(hours)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(timer)
    timer -= TimeUnit.MINUTES.toMillis(minutes)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(timer)

    val formattedTime = if (days > 0)
        String.format(Locale.getDefault(), WITH_DAYS, days, hours, minutes, seconds)
    else
        String.format(Locale.getDefault(), WITHOUT_DAYS, hours, minutes, seconds)

    Text(
        text = formattedTime,
        color = White,
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
    )
}


@Composable
fun BrandHomeRow(
    data: List<ResponseBrandHome.Data>?,
    isLoading: Boolean = false,
    onShowAllClicked: (() -> Unit)? = null,
    onBrandClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                            .placeholder(
                                enabled = true,
                                shape = CircleShape,
                                highlight = PlaceholderDefaults.shimmer
                            )
                    ) {}
                }
                Spacer(modifier = Modifier.width(8.dp))
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .width(120.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    ) {}
                } else {
                    Text(
                        text = stringResource(R.string.title_Selected_brands),
                        style = MaterialTheme.typography.titleLarge,
                        color = Eerie_Black
                    )
                }
            }
            TextButton(
                onClick = { if (!isLoading) onShowAllClicked?.invoke() }
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(20.dp)
                            .placeholder(
                                enabled = true,
                                shape = RoundedCornerShape(4.dp),
                                highlight = PlaceholderDefaults.shimmer
                            )
                    ) {}
                } else {
                    Text(
                        text = stringResource(R.string.title_see_all),
                        style = MaterialTheme.typography.titleLarge,
                        color = Green
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(vertical = 8.dp),
        ) {
            if (isLoading) {
                repeat(10) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                            .width(90.dp)
                    ) {
                        Card(
                            modifier = Modifier.size(80.dp),
                            shape = RoundedCornerShape(18.dp),
                            elevation = CardDefaults.cardElevation(2.dp),
                            colors = CardDefaults.cardColors(containerColor = White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .placeholder(
                                            enabled = true,
                                            highlight = PlaceholderDefaults.shimmer
                                        )
                                ) {}
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .height(18.dp)
                                .fillMaxWidth(0.8f)
                                .placeholder(
                                    enabled = true,
                                    shape = RoundedCornerShape(4.dp),
                                    highlight = PlaceholderDefaults.shimmer
                                )
                        ) {}
                    }
                }
            } else {
                data?.forEach { brand ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .width(90.dp)
                            .padding(horizontal = 6.dp)
                            .clickable(enabled = true) { brand.brandId?.let { onBrandClick(it.toString()) } }
                    ) {
                        Card(
                            modifier = Modifier.size(66.dp),
                            shape = RoundedCornerShape(10.dp),
                            elevation = CardDefaults.cardElevation(1.dp),
                            colors = CardDefaults.cardColors(containerColor = White)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ) {
                                LoadImage(
                                    url = brand.logo ?: "",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(56.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            }
                        }
                        Text(
                            text = brand.name ?: "",
                            modifier = Modifier.padding(top = 6.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = Eerie_Black,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadProductContent(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    data: List<ResponseProducts.Data.Data?>? = null,
    selectedCategory: ProductsCategories? = null,
    cardWidth: Dp = 140.dp,
    cardHeight: Dp = 200.dp,
    onViewAllClick: () -> Unit = {},
    onCategorySelected: ((ProductsCategories) -> Unit)? = null,
    onItemClick: (String) -> Unit = {}
) {
    Box(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {

        Box(
            Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(BestSellerBackground, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isLoading) {
                            Box(
                                modifier = Modifier
                                    .size(22.dp)
                                    .placeholder(
                                        enabled = true,
                                        shape = CircleShape,
                                        highlight = PlaceholderDefaults.shimmer
                                    )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .height(24.dp)
                                    .width(120.dp)
                                    .placeholder(
                                        enabled = true,
                                        shape = RoundedCornerShape(4.dp),
                                        highlight = PlaceholderDefaults.shimmer
                                    )
                            )
                        } else {
                            Icon(
                                painter = painterResource(R.drawable.best_seller),
                                contentDescription = null,
                                tint = Green,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(R.string.title_best_seller),
                                style = MaterialTheme.typography.titleLarge,
                                color = Eerie_Black
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(4.dp))
                if (selectedCategory != null && onCategorySelected != null) {
                    ProductCategoriesTabs(
                        selectedCategory = selectedCategory,
                        onCategorySelected = onCategorySelected,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 92.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (isLoading) {
                items(11) {
                    ProductTicketCard(
                        item = null,
                        isLoading = true,
                        cardWidth = cardWidth,
                        cardHeight = cardHeight,
                        onClick = {}
                    )
                }
            } else {

                val productList = data?.take(11).orEmpty()


                itemsIndexed(
                    items = productList,

                    key = { index, product ->
                        "${product?.idProduct ?: "null"}-$index"
                    }
                ) { _, item ->
                    ProductTicketCard(
                        item = item,
                        isLoading = false,
                        cardWidth = cardWidth,
                        cardHeight = cardHeight,
                        onClick = { item?.idProduct?.let { onItemClick(it.toString()) } }
                    )
                }

                item(key = VIEW_ALL_CARD) {
                    Box(
                        modifier = Modifier
                            .width(cardWidth)
                            .height(cardHeight)
                            .background(White, shape = RoundedCornerShape(12.dp))
                            .clickable { onViewAllClick() }
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.title_see_all),
                                color = Color.Black,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(R.drawable.arrow_circle_left),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductTicketCard(
    item: ResponseProducts.Data.Data?,
    isLoading: Boolean,
    cardWidth: Dp = 140.dp,
    cardHeight: Dp = 200.dp,
    onClick: () -> Unit
) {
    val originalPrice = item?.productPrice
    val discountedPrice = item?.productSpecificPrice?.specificPrice


    val hasDiscount = (originalPrice != null && discountedPrice != null && discountedPrice < originalPrice)

    val calculatedPercent = if (hasDiscount && originalPrice > 0) {
        val reduction = originalPrice - discountedPrice
        ((reduction / originalPrice) * 100).toInt()
    } else {
        0
    }


    Box(
        modifier = Modifier
            .width(cardWidth)
            .height(cardHeight)
            .background(White, shape = RoundedCornerShape(12.dp))
            .clickable(enabled = !isLoading, onClick = onClick)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.45f)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .placeholder(
                            enabled = true,
                            shape = RoundedCornerShape(12.dp),
                            highlight = PlaceholderDefaults.shimmer
                        )
                )
            } else {
                val imageUrl = item?.images?.homeDefault?.firstOrNull().orEmpty()
                LoadImage(
                    url = imageUrl,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }


            if (!isLoading) {

                if (hasDiscount && calculatedPercent > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 8.dp)
                            .background(
                                Spanish_Crimson,
                                shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "$calculatedPercent${stringResource(id = R.string.title_percentage)}",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(width = 32.dp, height = 16.dp)
                        .placeholder(enabled = true, highlight = PlaceholderDefaults.shimmer)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f))
                        )
                    )
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterStart)
                            .size(width = 80.dp, height = 12.dp)
                            .placeholder(enabled = true, highlight = PlaceholderDefaults.shimmer)
                    )
                } else {
                    item?.totalQty?.takeIf { it > 0 }?.let { qty ->
                        Text(
                            text = "${stringResource(id = R.string.title_remaining)} $qty",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .placeholder(
                            enabled = true,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderDefaults.shimmer
                        )
                )
            } else {
                item?.productName?.let { name ->
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium.copy(color = Eerie_Black),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(14.dp)
                        .placeholder(
                            enabled = true,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderDefaults.shimmer
                        )
                )
            } else if (hasDiscount) {
                Text(
                    text = originalPrice.moneySeparating(),
                    color = Spanish_Crimson,
                    style = MaterialTheme.typography.labelSmall.copy(textDecoration = TextDecoration.LineThrough),
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(2.dp))
            }

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(18.dp)
                        .placeholder(
                            enabled = true,
                            shape = RoundedCornerShape(4.dp),
                            highlight = PlaceholderDefaults.shimmer
                        )
                )
            } else {
                val finalPrice = discountedPrice ?: originalPrice
                Text(
                    text = finalPrice?.moneySeparating().orEmpty(),
                    color = Eerie_Black,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

fun mapResponseAmazingToUiModels(
    data: List<ResponseAmazing.Data.Data?>?,
    timer: Int?
): List<AmazingUiModel> {
    if (data.isNullOrEmpty()) return emptyList()

    return data.mapNotNull { item ->
        val productName = item?.productName ?: return@mapNotNull null
        val imageUrl = item.images?.largeDefault?.firstOrNull() ?: ""
        val oldPrice = item.productBasePrice ?: item.productPrice ?: 0


        val specific = item.productSpecificPrice
        val newPrice = specific?.specificPrice
            ?: item.productFinalPrice
            ?: oldPrice


        val discountPercent = specific?.discountPercent
            ?: ((if (oldPrice != 0) ((oldPrice - newPrice) * 100 / oldPrice) else 0))

        AmazingUiModel(
            id = item.idProduct ?: 0,
            title = productName,
            image = imageUrl,
            discountPercent = discountPercent,
            oldPrice = oldPrice,
            newPrice = newPrice,
            remainingQty = item.totalQty ?: 0,
            remainingTimeMillis = timer ?: 0
        )
    }
}


fun extractMainMenus(categoryState: NetworkRequest<ResponseCategory>): List<MainMenuModel> {
    val header = categoryState.data?.data?.get(0)
    val fashion = header?.childs?.get(0)
    return fashion?.childs
        ?.filter { it?.type == MENU_ITEM_TITLE }
        ?.map {
            MainMenuModel(
                title = it?.title.orEmpty(),
                imageUrl = "${BASE_URL_JSOUP}${it?.imageUrl}"
            )
        } ?: emptyList()
}

private fun getPersianCategoryName(category: ProductsCategories): String {
    return when (category) {
        ProductsCategories.FASHION -> "مد و پوشاک"
        ProductsCategories.COSMETICS -> "آرایشی و بهداشتی"
        ProductsCategories.HOME_APPLIANCES -> "لوازم خانه"
        ProductsCategories.CHILDREN_AND_ENTERTAINMENT -> "کودک و سرگرمی"
        ProductsCategories.SPORTS_AND_TRAVEL -> "ورزش و سفر"
        ProductsCategories.DIGITAL -> "کالای دیجیتال"
        ProductsCategories.STATIONERY -> "لوازم تحریر"
    }

}
