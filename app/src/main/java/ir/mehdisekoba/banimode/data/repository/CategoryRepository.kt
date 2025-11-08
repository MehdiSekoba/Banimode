package ir.mehdisekoba.banimode.data.repository

import ir.mehdisekoba.banimode.data.network.ApiServices
import javax.inject.Inject

class CategoryRepository  @Inject constructor(private val api: ApiServices) {
    suspend fun getMegaMenuData() = api.getMegaMenuData()
}