package ir.mehdisekoba.banimode.data.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseAmazing(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String?, // All flash sales products.
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "bani_time")
        val baniTime: String?, // 6
        @Json(name = "data")
        val `data`: List<Data?>?,
        @Json(name = "filters")
        val filters: Filters?,
        @Json(name = "time")
        val time: String?, // 15:23:46
        @Json(name = "timer")
        val timer: Int? // 9373000
    ) {
        @JsonClass(generateAdapter = true)
        data class Data(
            @Json(name = "all_colors_pwa")
            val allColorsPwa: List<AllColorsPwa?>?,
            @Json(name = "available_notify")
            val availableNotify: Boolean?, // true
            @Json(name = "banijet_tag")
            val banijetTag: Any?, // null
            @Json(name = "club_label")
            val clubLabel: Any?, // null
            @Json(name = "color_link")
            val colorLink: String?, // BM-460585/نيم-تنه-زنانه-هاليدي-كد-c691c3.html#/رنگ-سفید
            @Json(name = "color_link_new")
            val colorLinkNew: String?, // BM-460585/نيم-تنه-زنانه-هاليدي-كد-c691c3.html#/رنگ-سفید
            @Json(name = "color_name")
            val colorName: String?, // سفید
            @Json(name = "color_reference")
            val colorReference: String?, // 460585-1
            @Json(name = "color_slug")
            val colorSlug: String?, // سفید
            @Json(name = "color_value")
            val colorValue: String?, // #ffffff
            @Json(name = "flash_sale_message")
            val flashSaleMessage: Any?,
            @Json(name = "flash_sale_message_show")
            val flashSaleMessageShow: Boolean?, // false
            @Json(name = "id_color")
            val idColor: Int?, // 1
            @Json(name = "id_color_group")
            val idColorGroup: Int?, // 0
            @Json(name = "id_product")
            val idProduct: Int?, // 460585
            @Json(name = "id_product_attribute")
            val idProductAttribute: Int?, // 0
            @Json(name = "images")
            val images: Images?,
            @Json(name = "link")
            val link: String?, // BM-460585/نيم-تنه-زنانه-هاليدي-كد-c691c3.html#/رنگ-سفید
            @Json(name = "new_link")
            val newLink: String?,
            @Json(name = "product_base_price")
            val productBasePrice: Int?, // 368000
            @Json(name = "product_category_default_name_en")
            val productCategoryDefaultNameEn: String?,
            @Json(name = "product_category_size_guide")
            val productCategorySizeGuide: ProductCategorySizeGuide?,
            @Json(name = "product_final_price")
            val productFinalPrice: Int?, // 183900
            @Json(name = "product_manufacturer_en_name")
            val productManufacturerEnName: String?, // Holiday
            @Json(name = "product_manufacturer_id")
            val productManufacturerId: Int?, // 693
            @Json(name = "product_manufacturer_name")
            val productManufacturerName: String?, // هالیدی
            @Json(name = "product_manufacturer_slug")
            val productManufacturerSlug: String?, // هالیدی
            @Json(name = "product_name")
            val productName: String?, // تاپ زنانه هالیدی مدل C691C3
            @Json(name = "product_price")
            val productPrice: Int?, // 368000
            @Json(name = "product_reference")
            val productReference: String?, // AM-HOL-C691C3
            @Json(name = "product_score")
            val productScore: Any?, // null
            @Json(name = "product_size_guide")
            val productSizeGuide: List<List<String?>?>?,
            @Json(name = "product_specific_price")
            val productSpecificPrice: ProductSpecificPrice?,
            @Json(name = "promotion_label")
            val promotionLabel: Any?, // null
            @Json(name = "size")
            val size: List<Size?>?,
            @Json(name = "total_qty")
            val totalQty: Int?, // 10
            @Json(name = "videos")
            val videos: List<Any?>?,
            @Json(name = "view_type")
            val viewType: ViewType?
        ) {
            @JsonClass(generateAdapter = true)
            data class AllColorsPwa(
                @Json(name = "id")
                val id: Int?, // 1
                @Json(name = "name")
                val name: String?, // سفید
                @Json(name = "sizes")
                val sizes: List<Size?>?,
                @Json(name = "slug")
                val slug: String?, // سفید
                @Json(name = "value")
                val value: String? // #ffffff
            ) {
                @JsonClass(generateAdapter = true)
                data class Size(
                    @Json(name = "id_size")
                    val idSize: Int?, // 19
                    @Json(name = "name")
                    val name: String?, // XS
                    @Json(name = "slug")
                    val slug: String? // XS
                )
            }

            @JsonClass(generateAdapter = true)
            data class FlashSaleMessage(
                @Json(name = "text_desktop")
                val textDesktop: String?, //  تعداد باقیمانده از این محصول 
                @Json(name = "text_pwa")
                val textPwa: String?, //  تعداد باقیمانده 
                @Json(name = "total_qty")
                val totalQty: Int? // 3
            )

            @JsonClass(generateAdapter = true)
            data class Images(
                @Json(name = "cart_default")
                val cartDefault: List<String?>?,
                @Json(name = "home_default")
                val homeDefault: List<String?>?,
                @Json(name = "large_default")
                val largeDefault: List<String?>?,
                @Json(name = "medium_default")
                val mediumDefault: List<String?>?,
                @Json(name = "small_default")
                val smallDefault: List<String?>?,
                @Json(name = "thickbox_default")
                val thickboxDefault: List<String?>?,
                @Json(name = "thickbox_default2x")
                val thickboxDefault2x: List<String?>?,
                @Json(name = "zoom")
                val zoom: List<String?>?
            )

            @JsonClass(generateAdapter = true)
            data class ProductCategorySizeGuide(
                @Json(name = "bottom_description")
                val bottomDescription: String?,
                @Json(name = "image")
                val image: String?, // https://www.banimode.com/img/cms/040706/1759045648.png
                @Json(name = "top_description")
                val topDescription: String?
            )

            @JsonClass(generateAdapter = true)
            data class ProductSpecificPrice(
                @Json(name = "discount_amount")
                val discountAmount: Int?, // 184100
                @Json(name = "discount_percent")
                val discountPercent: Int?, // 50
                @Json(name = "from")
                val from: String?, // 2025-10-09 12:00:00
                @Json(name = "specific_price")
                val specificPrice: Int?, // 183900
                @Json(name = "to")
                val to: String? // 2025-10-09 17:59:59
            )

            @JsonClass(generateAdapter = true)
            data class Size(
                @Json(name = "active")
                val active: Int?, // 1
                @Json(name = "display_barcode")
                val displayBarcode: String?, // 140300013213031
                @Json(name = "extra_barcodes")
                val extraBarcodes: List<String?>?,
                @Json(name = "has_quantity")
                val hasQuantity: Int?, // 1
                @Json(name = "id_size")
                val idSize: Int?, // 19
                @Json(name = "name")
                val name: String?, // XS
                @Json(name = "position")
                val position: Int?, // 3
                @Json(name = "sellers")
                val sellers: List<Seller?>?,
                @Json(name = "show_in_filter")
                val showInFilter: Boolean?, // true
                @Json(name = "slug")
                val slug: String? // XS
            ) {
                @JsonClass(generateAdapter = true)
                data class Seller(
                    @Json(name = "barcode")
                    val barcode: String?, // 140300013213031
                    @Json(name = "base_price")
                    val basePrice: Int?, // 368000
                    @Json(name = "final_price")
                    val finalPrice: Int?, // 258000
                    @Json(name = "has_quantity")
                    val hasQuantity: Int?, // 1
                    @Json(name = "id_product_attribute")
                    val idProductAttribute: Int?, // 2151949
                    @Json(name = "id_seller")
                    val idSeller: Int?, // 1
                    @Json(name = "quantity")
                    val quantity: Int?, // 9
                    @Json(name = "score")
                    val score: Int?, // 5
                    @Json(name = "specific_price")
                    val specificPrice: SpecificPrice?,
                    @Json(name = "specific_price_date_from")
                    val specificPriceDateFrom: String?, // 2025-10-09 12:00:00
                    @Json(name = "specific_price_date_to")
                    val specificPriceDateTo: String? // 2025-10-09 17:59:59
                ) {
                    @JsonClass(generateAdapter = true)
                    data class SpecificPrice(
                        @Json(name = "discount_amount")
                        val discountAmount: Int?, // 184100
                        @Json(name = "discount_percent")
                        val discountPercent: Int?, // 50
                        @Json(name = "from")
                        val from: Any?, // null
                        @Json(name = "specific_price")
                        val specificPrice: Int?, // 183900
                        @Json(name = "to")
                        val to: Any? // null
                    )
                }
            }

            @JsonClass(generateAdapter = true)
            data class ViewType(
                @Json(name = "color_title")
                val colorTitle: String?, // رنگ
                @Json(name = "show_size_link")
                val showSizeLink: Boolean?, // true
                @Json(name = "size_title")
                val sizeTitle: String? // سایز
            )
        }

        @JsonClass(generateAdapter = true)
        data class Filters(
            @Json(name = "colors")
            val colors: List<Color?>?,
            @Json(name = "colors_group")
            val colorsGroup: List<ColorsGroup?>?,
            @Json(name = "manufacturers")
            val manufacturers: List<Manufacturer?>?,
            @Json(name = "sizes")
            val sizes: List<Size?>?
        ) {
            @JsonClass(generateAdapter = true)
            data class Color(
                @Json(name = "id")
                val id: Int?, // 10
                @Json(name = "name")
                val name: String?, // مشکی
                @Json(name = "value")
                val value: String? // #000000
            )

            @JsonClass(generateAdapter = true)
            data class ColorsGroup(
                @Json(name = "icon")
                val icon: String?, // https://www.banimode.com/img/cms/colorgroup/black.svg
                @Json(name = "id")
                val id: Int?, // 1
                @Json(name = "id_group")
                val idGroup: Int?, // 1
                @Json(name = "name")
                val name: String?, // مشکی
                @Json(name = "value")
                val value: String? // #000000
            )

            @JsonClass(generateAdapter = true)
            data class Manufacturer(
                @Json(name = "en_name")
                val enName: String?, // Narin Mode
                @Json(name = "id")
                val id: Int?, // 1462
                @Json(name = "name")
                val name: String? // نارین مد
            )

            @JsonClass(generateAdapter = true)
            data class Size(
                @Json(name = "id")
                val id: Int?, // 19
                @Json(name = "name")
                val name: String?, // XS
                @Json(name = "position")
                val position: Int? // 3
            )
        }
    }
}