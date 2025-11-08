package ir.mehdisekoba.banimode.data.repository

import ir.mehdisekoba.banimode.data.network.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiServices) {

    suspend fun getMegaMenuData() = api.getMegaMenuData()
    suspend fun getAmazingData() = api.getAmazingData()
    suspend fun getHomeBrandsData() = api.getHomeBrandsData()
    suspend fun getTopSellingProducts(queries: Map<String, String>) =
        api.getTopSellingProducts(queries)

}