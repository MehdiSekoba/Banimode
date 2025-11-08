package ir.mehdisekoba.banimode.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "errors")
    val errors: Map<String, List<String>>?,
    @Json(name = "message")
    val message: String?
)
