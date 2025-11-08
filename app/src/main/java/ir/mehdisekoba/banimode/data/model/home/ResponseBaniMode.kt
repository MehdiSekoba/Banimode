package ir.mehdisekoba.banimode.data.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseBaniMode(
    @Json(name = "data")
    val `data`: List<Data?>?,
    @Json(name = "message")
    val message: String?, // Get all menu
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "blocks")
        val blocks: List<Block?>?,
        @Json(name = "class")
        val classX: String?, // mega_menu_item
        @Json(name = "icon")
        val icon: String?, // https://www.banimode.com/modules/advancetopmenu/img/1714465337pJvZU37N.jpg
        @Json(name = "link")
        val link: String?, // https://www.banimode.com/3151/fashion-clothing
        @Json(name = "link_new")
        val linkNew: String?, // https://www.banimode.com/3151/fashion-clothing
        @Json(name = "position")
        val position: Int?, // 1
        @Json(name = "suggest_id")
        val suggestId: Int?, // 3151
        @Json(name = "target")
        val target: String?,
        @Json(name = "text")
        val text: String?,
        @Json(name = "title")
        val title: String?, // مد و پوشاک
        @Json(name = "type")
        val type: String? // img
    ) {
        @JsonClass(generateAdapter = true)
        data class Block(
            @Json(name = "class")
            val classX: String?, // list
            @Json(name = "items")
            val items: List<Item?>?,
            @Json(name = "position")
            val position: Int?, // 0
            @Json(name = "width")
            val width: Int? // 3
        ) {
            @JsonClass(generateAdapter = true)
            data class Item(
                @Json(name = "class")
                val classX: String?, // line
                @Json(name = "icon")
                val icon: String?, // https://www.banimode.com/modules/advancetopmenu/img/1714467102eQrbtw1J.jpg
                @Json(name = "id_item")
                val idItem: Int?, // 2668
                @Json(name = "link")
                val link: String?, // https://www.banimode.com/1382/teenage-products-for-girls-and-boys
                @Json(name = "link_new")
                val linkNew: String?, // https://www.banimode.com/1382/teenage-products-for-girls-and-boys
                @Json(name = "position")
                val position: Int?, // 0
                @Json(name = "suggest_id")
                val suggestId: Int?, // 1382
                @Json(name = "target")
                val target: String?, // direct
                @Json(name = "text")
                val text: String?,
                @Json(name = "title")
                val title: String?, // محصولات نوجوان
                @Json(name = "type")
                val type: String? // img
            )
        }
    }
}