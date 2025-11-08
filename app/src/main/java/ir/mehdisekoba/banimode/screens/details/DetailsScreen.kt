package ir.mehdisekoba.banimode.screens.details

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.data.model.details.ResponseComments
import ir.mehdisekoba.banimode.data.model.details.ResponseDetail
import ir.mehdisekoba.banimode.data.model.details.ResponseRelated
import ir.mehdisekoba.banimode.screens.theme.AuraMetalMaurus
import ir.mehdisekoba.banimode.screens.theme.Cadet_Grey
import ir.mehdisekoba.banimode.screens.theme.Castleton_Green
import ir.mehdisekoba.banimode.screens.theme.Cultured
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Ghost_White
import ir.mehdisekoba.banimode.screens.theme.Green
import ir.mehdisekoba.banimode.screens.theme.White
import ir.mehdisekoba.banimode.utils.LoadImageWithShimmerAndError
import ir.mehdisekoba.banimode.utils.ShowErrorIfNeeded
import ir.mehdisekoba.banimode.utils.TopAppBarWithBack
import ir.mehdisekoba.banimode.utils.moneySeparating
import ir.mehdisekoba.banimode.utils.network.NetworkRequest
import ir.mehdisekoba.banimode.viewmodel.DetailViewModel


@Composable
fun DetailsScreen(
    itemId: String,
    onBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailState by viewModel.detailData.collectAsStateWithLifecycle()
    val commentsState by viewModel.commentsData.collectAsStateWithLifecycle()
    val similarState by viewModel.similarProduct.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(itemId) {
        viewModel.callDetailApi(itemId)
        viewModel.callCommentsProducts(itemId)
    }

    LaunchedEffect(detailState) {
        val categoryId =
            (detailState as? NetworkRequest.Success)?.data?.data?.product?.productCategoryDefaultId
        if (categoryId != null) {
            viewModel.callSimilarProducts(categoryId)
        }
    }

    when (detailState) {
        is NetworkRequest.Loading -> ProductDetailsContent(isLoading = true)
        is NetworkRequest.Success -> {
            val data = detailState.data?.data
            if (data != null) {
                ProductDetailsContent(
                    isLoading = false,
                    data = data,
                    commentsState = commentsState,
                    similarState = similarState,
                    snackbarHostState = snackbarHostState,
                    onBackClick = onBack,
                    onProductClick = { viewModel.callDetailApi(it) }
                )
            } else {
                ProductDetailsContent(isLoading = false)
            }
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(
                isError = true,
                errorMessage = detailState.error.orEmpty(),
                snackbarHostState = snackbarHostState
            )
            ProductDetailsContent(isLoading = false)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProductDetailsContent(
    isLoading: Boolean,
    data: ResponseDetail.Data? = null,
    commentsState: NetworkRequest<ResponseComments>? = null,
    similarState: NetworkRequest<ResponseRelated>? = null,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onBackClick: () -> Unit = {},
    onProductClick: (String) -> Unit = {}
) {
    if (isLoading || data == null) {
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            CircularWavyProgressIndicator(color = Green, trackColor = Castleton_Green)
        }
        return
    }

    val colors = data.colors ?: emptyList()
    if (colors.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.not_found_color))
        }
        return
    }

    var selectedColorName by rememberSaveable { mutableStateOf(colors.first().colorName.orEmpty()) }
    val selectedColorObj = colors.find { it.colorName == selectedColorName } ?: colors.first()
    val imageUrls = selectedColorObj.newImages?.mapNotNull { it.imageSize?.zoom } ?: emptyList()
    var selectedImageIndex by remember { mutableIntStateOf(0) }
    val currentImageUrl = imageUrls.getOrNull(selectedImageIndex).orEmpty()
    val sizes = selectedColorObj.size ?: emptyList()
    var selectedSize by remember { mutableStateOf(sizes.firstOrNull()?.name.orEmpty()) }

    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.specifications),
        stringResource(R.string.product_review),
        stringResource(R.string.user_comments)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Cultured,
        topBar = {
            TopAppBarWithBack(
                title = data.product?.productMetaTitle.orEmpty(),
                onBackClick
            )
        },
        bottomBar = {
            ProductBottomBar(
                basePrice = selectedColorObj.productBasePrice,
                finalPrice = selectedColorObj.productFinalPrice,
                discountPercent = selectedColorObj.productSpecificPrice?.discountPercent,
                onAddToCartClick = {},
                modifier = Modifier
                    .navigationBarsPadding()
                    .background(Cultured)
                    .shadow(0.dp)
            )
        },


        ) { padding ->
        val bottomBarHeight = 80.dp

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    start = 0.dp,
                    end = 0.dp
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = bottomBarHeight + 8.dp)
        )
        {

            item {
                if (currentImageUrl.isNotEmpty()) {
                    LoadImageWithShimmerAndError(
                        imageUrl = currentImageUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        shimmerEnabled = true
                    )
                }
            }


            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-82).dp),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = Ghost_White),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(34.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.line),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp),
                                tint = AuraMetalMaurus
                            )
                        }

                        ImageThumbnailRow(
                            imageUrls = imageUrls,
                            selectedIndex = selectedImageIndex,
                            onImageSelected = { selectedImageIndex = it }
                        )

                        Text(
                            text = data.product?.productMetaTitle.orEmpty(),
                            style = MaterialTheme.typography.titleLarge,
                            color = Eerie_Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        )

                        ProductItemColor(
                            colors = colors,
                            selectedColor = selectedColorName,
                            onColorSelected = { newColor ->
                                selectedColorName = newColor
                                selectedImageIndex = 0
                            }
                        )

                        ProductAvailableSize(
                            sizes = selectedColorObj.size ?: emptyList(),
                            selectedSize = selectedSize,
                            onSizeSelected = { selectedSize = it }
                        )

                        PrimaryTabRow(
                            selectedTabIndex = selectedTabIndex,
                            containerColor = White,
                            contentColor = Eerie_Black,
                            indicator = {
                                TabRowDefaults.PrimaryIndicator(
                                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                                    height = 3.dp,
                                    color = Green
                                )
                            },
                            divider = {
                                HorizontalDivider(
                                    color = Color.LightGray,
                                    thickness = 1.dp
                                )
                            }
                        ) {
                            tabs.forEachIndexed { index, title ->
                                val isSelected = selectedTabIndex == index
                                val textStyle = if (isSelected)
                                    MaterialTheme.typography.titleLarge
                                else
                                    MaterialTheme.typography.titleMedium

                                Tab(
                                    selected = isSelected,
                                    onClick = { selectedTabIndex = index },
                                    text = {
                                        Text(
                                            text = title,
                                            color = if (isSelected) Eerie_Black else Color.Gray,
                                            style = textStyle
                                        )
                                    }
                                )
                            }

                        }

                        when (selectedTabIndex) {
                            0 -> ProductFeaturesSection(data.product?.productFeatures.orEmpty())
                            1 -> ExpandableText(description = data.product?.productDescriptionShort)
                            2 -> {
                                ProductCommentsSectionState(
                                    state = commentsState ?: NetworkRequest.Loading(),
                                    snackbarHostState = snackbarHostState
                                )
                            }
                        }
                        if (similarState != null) {
                            SimilarProductContent(
                                state = similarState,
                                snackbarHostState = snackbarHostState,
                                onProductClick = onProductClick
                            )
                        }


                    }
                }
            }
        }
    }
}


@Composable
fun ImageThumbnailRow(
    imageUrls: List<String>,
    selectedIndex: Int,
    onImageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (imageUrls.size <= 1) {
        Spacer(modifier = modifier.height(1.dp))
        return
    }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(imageUrls.size) { index ->
            Box(
                modifier = Modifier
                    .size(62.dp)
                    .border(
                        color = if (index == selectedIndex) Green else Cadet_Grey,
                        width = 2.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { onImageSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                LoadImageWithShimmerAndError(
                    imageUrl = imageUrls[index],
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

@Composable
fun ProductItemColor(
    colors: List<ResponseDetail.Data.Color>,
    selectedColor: String,
    onColorSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.title_colors),
            color = Eerie_Black,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(6.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(colors) { colorObj ->
                val hex = colorObj.colorValue ?: "#00FFFFFF"
                val color = try {
                    Color(hex.toColorInt())
                } catch (_: Exception) {
                    Color.White
                }

                val isSelected = colorObj.colorName == selectedColor

                val animatedSize by animateDpAsState(
                    targetValue = if (isSelected) 44.dp else 36.dp,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    label = "color size animation"
                )

                val animatedElevation by animateDpAsState(
                    targetValue = if (isSelected) 6.dp else 0.dp,
                    animationSpec = tween(durationMillis = 250),
                    label = "elevation animation"
                )

                Box(
                    modifier = Modifier
                        .size(animatedSize)
                        .shadow(animatedElevation, CircleShape, clip = false)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = if (isSelected) Green else Color.LightGray,
                            shape = CircleShape
                        )
                        .clickable {
                            colorObj.colorName?.let { onColorSelected(it) }
                        }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAvailableSize(
    sizes: List<ResponseDetail.Data.Color.Size>,
    selectedSize: String,
    onSizeSelected: (String) -> Unit
) {
    if (sizes.isEmpty()) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.title_sizes),
            color = Eerie_Black,
            style = MaterialTheme.typography.titleLarge,
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            items(sizes) { size ->
                val name = size.name ?: return@items
                val isSelected = name == selectedSize

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (isSelected) Green.copy(alpha = 0.15f) else Color.Transparent,
                    border = BorderStroke(
                        1.5.dp,
                        if (isSelected) Green else Color.Gray.copy(alpha = 0.4f)
                    ),
                    modifier = Modifier
                        .height(28.dp)
                        .clickable { onSizeSelected(name) },
                    tonalElevation = if (isSelected) 3.dp else 0.dp,
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = name,
                            modifier = Modifier.wrapContentHeight(),
                            color = if (isSelected) Green else Eerie_Black,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductFeaturesSection(features: List<ResponseDetail.Data.Product.ProductFeature>) {
    if (features.isEmpty()) {
        Text(
            text = stringResource(R.string.no_features),
            color = Eerie_Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        return
    }

    val maxVisible = 5
    var expanded by remember { mutableStateOf(false) }
    val visible = if (expanded) features else features.take(maxVisible)

    Column {
        visible.filter { !it.title.isNullOrBlank() && !it.value.isNullOrBlank() }
            .forEachIndexed { index, feature ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (index % 2 == 0) Color(0xFFF5F5F5) else Color.Transparent)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = feature.title!!,
                        style = MaterialTheme.typography.titleLarge.copy(lineHeight = 24.sp),
                        fontWeight = FontWeight.Medium,
                        color = Eerie_Black,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = feature.value!!,
                        style = MaterialTheme.typography.titleMedium.copy(lineHeight = 24.sp),
                        color = Color.Gray,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

        if (features.size > maxVisible) {
            Text(
                text = if (expanded) stringResource(R.string.title_show_less)
                else stringResource(R.string.title_show_more),
                color = Green,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ExpandableText(
    description: String?,
    modifier: Modifier = Modifier
) {
    if (description.isNullOrBlank()) {
        Text(
            text = stringResource(R.string.no_description),
            style = MaterialTheme.typography.titleLarge,
            color = Eerie_Black,
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center

        )
    } else {
        var expanded by remember { mutableStateOf(false) }

        Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = AnnotatedString.fromHtml(description),
                style = MaterialTheme.typography.titleLarge,
                color = Eerie_Black,
                maxLines = if (expanded) Int.MAX_VALUE else 4,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = if (expanded) stringResource(R.string.title_show_less) else stringResource(R.string.title_show_more),
                color = Green,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { expanded = !expanded }
            )
        }
    }
}

@Composable
fun ProductCommentsSectionState(
    state: NetworkRequest<ResponseComments>,
    snackbarHostState: SnackbarHostState
) {
    when (state) {
        is NetworkRequest.Loading -> Box(
            Modifier
                .fillMaxWidth()
                .height(160.dp), Alignment.Center
        ) {
            CircularProgressIndicator(color = Green)
        }

        is NetworkRequest.Success -> {
            val comments = state.data?.data?.comments ?: emptyList()
            ProductCommentsSection(comments)
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(true, state.error.orEmpty(), snackbarHostState)
            ProductCommentsSection(emptyList())
        }
    }
}

@Composable
fun ProductCommentsSection(comments: List<ResponseComments.Data.Comment>) {
    if (comments.isEmpty()) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.directbox_notif),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Gray
                )
                Text(
                    stringResource(R.string.user_comments_is_empty),
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        return

    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(comments) { comment ->
            Card(
                modifier = Modifier
                    .width(210.dp)
                    .height(160.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(comment.grade ?: 0) {
                            Icon(
                                painter = painterResource(R.drawable.star),
                                contentDescription = null,
                                tint = Color(0xFFFFC107),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        Text(
                            text = comment.title ?: "",
                            color = Eerie_Black,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 6.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = comment.content ?: "",
                        color = Color.Gray,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (!comment.answer.isNullOrBlank()) {
                        Spacer(Modifier.height(8.dp))
                        Text(
                            stringResource(R.string.seller_response),
                            color = Green,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            comment.answer,
                            color = Eerie_Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(Modifier.weight(1f))

                    Text(
                        comment.customerName.orEmpty(),
                        color = Green,
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }
}

@Composable
fun SimilarProductContent(
    state: NetworkRequest<ResponseRelated>,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onProductClick: (String) -> Unit = {}
) {
    when (state) {
        is NetworkRequest.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Green)
            }
        }

        is NetworkRequest.Error -> {
            ShowErrorIfNeeded(true, state.error.orEmpty(), snackbarHostState)
        }

        is NetworkRequest.Success -> {
            val products = state.data?.data?.data.orEmpty().filterNotNull()
            if (products.isEmpty()) return

            Column(modifier = modifier.padding(vertical = 8.dp)) {
                Text(
                    text = stringResource(R.string.similar_products),
                    style = MaterialTheme.typography.titleLarge,
                    color = Eerie_Black,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(products) { product ->
                        Card(
                            modifier = Modifier
                                .width(160.dp)
                                .height(260.dp)
                                .clickable { product.idProduct?.let { onProductClick(it.toString()) } },
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(4.dp),
                            colors = CardDefaults.cardColors(containerColor = Ghost_White)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val imageUrl = product.images?.zoom?.firstOrNull().orEmpty()
                                if (imageUrl.isNotEmpty()) {
                                    LoadImageWithShimmerAndError(
                                        imageUrl = imageUrl,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(120.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        shimmerEnabled = true
                                    )
                                }

                                Text(
                                    text = product.productName.orEmpty(),
                                    style = MaterialTheme.typography.titleMedium,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    val discountPercent = product.productSpecificPrice?.discountPercent ?: 0
                                    val basePrice = product.productBasePrice ?: 0
                                    val finalPrice = product.productFinalPrice ?: 0

                                    Text(
                                        text = finalPrice.moneySeparating(),
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = if (discountPercent > 0) Green else Eerie_Black,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )

                                    if (discountPercent > 0 && basePrice > finalPrice) {
                                        Text(
                                            text = basePrice.moneySeparating(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                textDecoration = TextDecoration.LineThrough,
                                                color = Color.Gray
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
