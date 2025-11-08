package ir.mehdisekoba.banimode.data.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseComments(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String?, 
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "comments")
        val comments: List<Comment>?,
        @Json(name = "links")
        val links: Links?,
        @Json(name = "media_comment")
        val mediaComment: List<Any>?,
        @Json(name = "meta")
        val meta: Meta?
    ) {
        @JsonClass(generateAdapter = true)
        data class Comment(
            @Json(name = "answer")
            val answer: String?,
            @Json(name = "attachments")
            val attachments: List<Any>?,
            @Json(name = "content")
            val content: String?, // خوبه
            @Json(name = "customer_name")
            val customerName: String?, // کاربر بانی مد
            @Json(name = "date_add")
            val dateAdd: String?, // 2025-09-29T15:42:17.000000Z
            @Json(name = "date_answer")
            val dateAnswer: String?, // 0000-00-00 00:00:00
            @Json(name = "feedbacks")
            val feedbacks: List<Any>?,
            @Json(name = "grade")
            val grade: Int?, // 5
            @Json(name = "id")
            val id: Int?, // 760393
            @Json(name = "is_buyer")
            val isBuyer: Int?, // 1
            @Json(name = "rate")
            val rate: Int?, // 0
            @Json(name = "sender")
            val sender: String?,
            @Json(name = "title")
            val title: String? // خوبه
        )

        @JsonClass(generateAdapter = true)
        data class Links(
            @Json(name = "first")
            val first: String?, // https://mobapi.banimode.com/api/v2/products/134620/comments?page=1
            @Json(name = "last")
            val last: String?, // https://mobapi.banimode.com/api/v2/products/134620/comments?page=1
            @Json(name = "next")
            val next: Any?, // null
            @Json(name = "prev")
            val prev: Any? // null
        )

        @JsonClass(generateAdapter = true)
        data class Meta(
            @Json(name = "current_page")
            val currentPage: Int?, // 1
            @Json(name = "from")
            val from: Int?, // 1
            @Json(name = "last_page")
            val lastPage: Int?, // 1
            @Json(name = "per_page")
            val perPage: Int?, // 10
            @Json(name = "to")
            val to: Int?, // 11
            @Json(name = "total")
            val total: Int? // 10
        )
    }
}