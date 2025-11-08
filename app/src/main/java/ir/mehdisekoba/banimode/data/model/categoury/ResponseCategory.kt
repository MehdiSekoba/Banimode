package ir.mehdisekoba.banimode.data.model.categoury

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCategory(
    @Json(name = "data")
    val `data`: List<Data?>?,
    @Json(name = "message")
    val message: String?, // Get mega menu
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "active")
        val active: Boolean?, // true
        @Json(name = "childs")
        val childs: List<Child?>?,
        @Json(name = "css_class")
        val cssClass: String?,
        @Json(name = "extra_data")
        val extraData: String?,
        @Json(name = "hover_text")
        val hoverText: String?,
        @Json(name = "icon_url")
        val iconUrl: String?, // /img/menu/icon/1681628415_844318.svg
        @Json(name = "id")
        val id: Int?, // 1
        @Json(name = "image_url")
        val imageUrl: String?,
        @Json(name = "link")
        val link: String?, // #
        @Json(name = "position")
        val position: Int?, // 1
        @Json(name = "target")
        val target: String?, // blank
        @Json(name = "title")
        val title: String?, // دسته بندی کالاها
        @Json(name = "type")
        val type: String? // top_menu_mega_header
    ) {
        @JsonClass(generateAdapter = true)
        data class Child(
            @Json(name = "active")
            val active: Boolean?, // true
            @Json(name = "childs")
            val childs: List<Child?>?,
            @Json(name = "css_class")
            val cssClass: String?,
            @Json(name = "extra_data")
            val extraData: String?,
            @Json(name = "hover_text")
            val hoverText: String?,
            @Json(name = "icon_url")
            val iconUrl: String?, // /img/menu/icon/1717316137_538434.svg
            @Json(name = "id")
            val id: Int?, // 14
            @Json(name = "image_url")
            val imageUrl: String?,
            @Json(name = "link")
            val link: String?, // /category/fashion
            @Json(name = "position")
            val position: Int?, // 1
            @Json(name = "target")
            val target: String?, // self
            @Json(name = "title")
            val title: String?, // مد و پوشاک
            @Json(name = "type")
            val type: String? // mega_sub_header
        ) {
            @JsonClass(generateAdapter = true)
            data class Child(
                @Json(name = "active")
                val active: Boolean?, // true
                @Json(name = "childs")
                val childs: List<Child?>?,
                @Json(name = "css_class")
                val cssClass: String?,
                @Json(name = "extra_data")
                val extraData: String?,
                @Json(name = "hover_text")
                val hoverText: String?,
                @Json(name = "icon_url")
                val iconUrl: String?,
                @Json(name = "id")
                val id: Int?, // 16
                @Json(name = "image_url")
                val imageUrl: String?, // /img/menu/image/1716999374_420572.png
                @Json(name = "link")
                val link: String?, // /3/%D9%84%D8%A8%D8%A7%D8%B3-%D9%85%D8%B1%D8%AF%D8%A7%D9%86%D9%87
                @Json(name = "position")
                val position: Int?, // 1
                @Json(name = "target")
                val target: String?, // self
                @Json(name = "title")
                val title: String?, // لباس مردانه
                @Json(name = "type")
                val type: String? // menu_item_title
            ) {
                @JsonClass(generateAdapter = true)
                data class Child(
                    @Json(name = "active")
                    val active: Boolean?, // true
                    @Json(name = "childs")
                    val childs: List<Any?>?,
                    @Json(name = "css_class")
                    val cssClass: String?,
                    @Json(name = "extra_data")
                    val extraData: String?,
                    @Json(name = "hover_text")
                    val hoverText: String?,
                    @Json(name = "icon_url")
                    val iconUrl: String?,
                    @Json(name = "id")
                    val id: Int?, // 149
                    @Json(name = "image_url")
                    val imageUrl: String?,
                    @Json(name = "link")
                    val link: String?, // /883/%DA%A9%D8%A7%D9%BE%D8%B4%D9%86-%D9%85%D8%B1%D8%AF%D8%A7%D9%86%D9%87
                    @Json(name = "position")
                    val position: Int?, // 1
                    @Json(name = "target")
                    val target: String?, // self
                    @Json(name = "title")
                    val title: String?, // کاپشن مردانه
                    @Json(name = "type")
                    val type: String? // menu_item
                )
            }
        }
    }
}