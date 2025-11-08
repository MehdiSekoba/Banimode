package ir.mehdisekoba.banimode.data.network

import ir.mehdisekoba.banimode.data.model.details.ResponseComments
import ir.mehdisekoba.banimode.data.model.details.ResponseDetail
import ir.mehdisekoba.banimode.data.model.details.ResponseRelated
import ir.mehdisekoba.banimode.data.model.home.ResponseAmazing
import ir.mehdisekoba.banimode.data.model.home.ResponseBrandHome
import ir.mehdisekoba.banimode.data.model.categoury.ResponseCategory
import ir.mehdisekoba.banimode.data.model.home.ResponseProducts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiServices {
    @GET("")
    suspend fun getMegaMenuData(): Response<ResponseCategory>

    @GET("")
    suspend fun getAmazingData(): Response<ResponseAmazing>

    @GET("")
    suspend fun getHomeBrandsData(): Response<ResponseBrandHome>
    @GET("")
    suspend fun getTopSellingProducts(
        @QueryMap queries: Map<String, String>
    ): Response<ResponseProducts>

     @GET("")
    suspend fun  getDetailProduct(@Path("id") id: String): Response<ResponseDetail>

    @GET("")
    suspend fun getCommentsProduct(
        @Path("id") id: String,
        @Query("page") page: Int
    ): Response<ResponseComments>

    @GET("")
    suspend fun getRelatedByCategory(
        @Query("related_category") categories: Int
    ): Response<ResponseRelated>

}