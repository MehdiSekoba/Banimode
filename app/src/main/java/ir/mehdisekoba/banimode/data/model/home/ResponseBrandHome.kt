package ir.mehdisekoba.banimode.data.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseBrandHome(
    @Json(name = "data")
    val `data`: List<Data?>?,
    @Json(name = "message")
    val message: String?, // Get all popular manufacturers
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "brand_id")
        val brandId: Int?, // 1
        @Json(name = "logo")
        val logo: String?, // https://www.banimode.com/img/m/1-brand-136x69.jpg
        @Json(name = "name")
        val name: String?, // جین وست
        @Json(name = "slug")
        val slug: String? // جین_وست
    )
}