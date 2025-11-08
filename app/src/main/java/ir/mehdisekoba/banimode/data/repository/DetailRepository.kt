package ir.mehdisekoba.banimode.data.repository

import ir.mehdisekoba.banimode.data.network.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getDetailProduct(id: String) = api.getDetailProduct(id)
    suspend fun getCommentsProduct(id: String,page: Int= 1) = api.getCommentsProduct(id,page)
    suspend fun getSimilarProduct(categories: Int) = api.getRelatedByCategory(categories)

}