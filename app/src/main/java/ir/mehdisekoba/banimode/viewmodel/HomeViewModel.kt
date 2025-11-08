package ir.mehdisekoba.banimode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mehdisekoba.banimode.data.model.categoury.ResponseCategory
import ir.mehdisekoba.banimode.data.model.home.ResponseAmazing
import ir.mehdisekoba.banimode.data.model.home.ResponseBrandHome
import ir.mehdisekoba.banimode.data.model.home.ResponseProducts
import ir.mehdisekoba.banimode.data.repository.HomeRepository
import ir.mehdisekoba.banimode.utils.ATTRIBUTE_KEY_SELECT
import ir.mehdisekoba.banimode.utils.BASE_URL_JSOUP
import ir.mehdisekoba.banimode.utils.CATEGORY_ID
import ir.mehdisekoba.banimode.utils.DOCUMENT_SELECTED_ELEMENT
import ir.mehdisekoba.banimode.utils.MIN_QTY
import ir.mehdisekoba.banimode.utils.MIN_QTY_DEFAULT
import ir.mehdisekoba.banimode.utils.PAGE
import ir.mehdisekoba.banimode.utils.PAGE_SIZE
import ir.mehdisekoba.banimode.utils.PAGE_SIZE_DEFAULT
import ir.mehdisekoba.banimode.utils.ProductsCategories
import ir.mehdisekoba.banimode.utils.network.NetworkRequest
import ir.mehdisekoba.banimode.utils.network.NetworkResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    //banner
    private val _banners = MutableStateFlow<NetworkRequest<List<String>>>(NetworkRequest.Loading())
    val banners: StateFlow<NetworkRequest<List<String>>> = _banners

    //Category
    private val _categoryHome =
        MutableStateFlow<NetworkRequest<ResponseCategory>>(NetworkRequest.Loading())
    val categoryHome: StateFlow<NetworkRequest<ResponseCategory>> = _categoryHome

    //amazing
    private val _amazingData =
        MutableStateFlow<NetworkRequest<ResponseAmazing>>(NetworkRequest.Loading())
    val amazingData: StateFlow<NetworkRequest<ResponseAmazing>> = _amazingData

    //brand
    private val _brandData =
        MutableStateFlow<NetworkRequest<ResponseBrandHome>>(NetworkRequest.Loading())
    val brandData: StateFlow<NetworkRequest<ResponseBrandHome>> = _brandData

    //best seller
    private val _bestSeller =
        MutableStateFlow<NetworkRequest<ResponseProducts>>(NetworkRequest.Loading())
    val bestSeller = _bestSeller.asStateFlow()



    fun loadBanners() = viewModelScope.launch(IO) {
        _banners.value = NetworkRequest.Loading()
        try {
            val doc = Jsoup.connect(BASE_URL_JSOUP).get()
            val images = doc.select(DOCUMENT_SELECTED_ELEMENT)
            val imgUrls = images.map { it.absUrl(ATTRIBUTE_KEY_SELECT) }
            val urls = if (imgUrls.size > 4) imgUrls.take(4) else imgUrls
            _banners.value = NetworkRequest.Success(urls)
        } catch (e: Exception) {
            _banners.value = NetworkRequest.Error("خطا در دریافت بنرها: ${e.message}")
        }

    }

    fun loadingCategoryHome() = viewModelScope.launch(IO) {
        _categoryHome.value = NetworkRequest.Loading()
        val response = repository.getMegaMenuData()
        _categoryHome.value = NetworkResponse(response).generateResponse()
    }

    fun loadingAmazingData() = viewModelScope.launch {
        _amazingData.value = NetworkRequest.Loading()
        val response = repository.getAmazingData()
        _amazingData.value = NetworkResponse(response).generateResponse()

    }

    fun loadingBrandHome() = viewModelScope.launch {
        _brandData.value = NetworkRequest.Loading()
        val response = repository.getHomeBrandsData()
        _brandData.value = NetworkResponse(response).generateResponse()

    }

    private fun productsQueries(category: ProductsCategories): Map<String, String> {
        return mapOf(
            "sort[${category.sortType}]" to category.sortValue,
            CATEGORY_ID to category.id,
            MIN_QTY to MIN_QTY_DEFAULT,
            PAGE_SIZE to PAGE_SIZE_DEFAULT,
            PAGE to "1"
        )
    }


    fun getProductsData(category: ProductsCategories) = viewModelScope.launch {
        _bestSeller.value = NetworkRequest.Loading() // Show loading state
        val response = repository.getTopSellingProducts(productsQueries(category))
        _bestSeller.value = NetworkResponse(response).generateResponse()
    }
}