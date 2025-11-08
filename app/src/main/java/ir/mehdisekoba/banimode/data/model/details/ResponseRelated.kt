package ir.mehdisekoba.banimode.data.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseRelated(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String?, // List of products.
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "data")
        val `data`: List<Data?>?,
        @Json(name = "filters")
        val filters: Any?, // null
        @Json(name = "links")
        val links: Links?,
        @Json(name = "meta")
        val meta: Meta?,
        @Json(name = "url")
        val url: String?
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
            val clubLabel: ClubLabel?,
            @Json(name = "color_link")
            val colorLink: String?, // BM-460414/كفش-زنانه-چرم-مصنوعي-پاريس-هيلتون-مدل-psw311.html?click_id=EL__a04636fff7144a9abe9eba02ca7779de__1#/رنگ-قرمز
            @Json(name = "color_link_new")
            val colorLinkNew: String?, // BM-460414/كفش-زنانه-چرم-مصنوعي-پاريس-هيلتون-مدل-psw311.html?click_id=EL__a04636fff7144a9abe9eba02ca7779de__1#/رنگ-قرمز
            @Json(name = "color_name")
            val colorName: String?, // قرمز
            @Json(name = "color_reference")
            val colorReference: String?, // 460414-16
            @Json(name = "color_slug")
            val colorSlug: String?, // قرمز
            @Json(name = "color_value")
            val colorValue: String?, // #FF0000
            @Json(name = "flash_sale_message")
            val flashSaleMessage: List<Any?>?,
            @Json(name = "flash_sale_message_show")
            val flashSaleMessageShow: Boolean?, // false
            @Json(name = "id_color")
            val idColor: Int?, // 16
            @Json(name = "id_color_group")
            val idColorGroup: Int?, // 0
            @Json(name = "id_product")
            val idProduct: Int?, // 460414
            @Json(name = "id_product_attribute")
            val idProductAttribute: Int?, // 0
            @Json(name = "images")
            val images: Images?,
            @Json(name = "link")
            val link: String?, // BM-460414/كفش-زنانه-چرم-مصنوعي-پاريس-هيلتون-مدل-psw311.html#/رنگ-قرمز
            @Json(name = "new_link")
            val newLink: String?, // BM-460414/كفش-زنانه-چرم-مصنوعي-پاريس-هيلتون-مدل-psw311.html?color=رنگ-قرمز
            @Json(name = "product_base_price")
            val productBasePrice: Int?, // 2680000
            @Json(name = "product_category_default_name_en")
            val productCategoryDefaultNameEn: String?,
            @Json(name = "product_category_size_guide")
            val productCategorySizeGuide: ProductCategorySizeGuide?,
            @Json(name = "product_final_price")
            val productFinalPrice: Int?, // 1876000
            @Json(name = "product_manufacturer_en_name")
            val productManufacturerEnName: String?, // Paris Hilton
            @Json(name = "product_manufacturer_id")
            val productManufacturerId: Int?, // 821
            @Json(name = "product_manufacturer_name")
            val productManufacturerName: String?, // پاریس هیلتون
            @Json(name = "product_manufacturer_slug")
            val productManufacturerSlug: String?, // پاریس_هیلتون
            @Json(name = "product_name")
            val productName: String?, // کفش پاشنه بلند زنانه پاریس هیلتون مدل psw311
            @Json(name = "product_price")
            val productPrice: Int?, // 2680000
            @Json(name = "product_reference")
            val productReference: String?, // AM-PSW311
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
            val totalQty: Int?, // 5
            @Json(name = "videos")
            val videos: List<Any?>?,
            @Json(name = "view_type")
            val viewType: ViewType?
        ) {
            @JsonClass(generateAdapter = true)
            data class AllColorsPwa(
                @Json(name = "id")
                val id: Int?, // 16
                @Json(name = "name")
                val name: String?, // قرمز
                @Json(name = "sizes")
                val sizes: List<Size?>?,
                @Json(name = "slug")
                val slug: String?, // قرمز
                @Json(name = "value")
                val value: String? // #FF0000
            ) {
                @JsonClass(generateAdapter = true)
                data class Size(
                    @Json(name = "id_size")
                    val idSize: Int?, // 113
                    @Json(name = "name")
                    val name: String?, // 37
                    @Json(name = "slug")
                    val slug: String? // 37
                )
            }

            @JsonClass(generateAdapter = true)
            data class ClubLabel(
                @Json(name = "background_color")
                val backgroundColor: String?,
                @Json(name = "icon")
                val icon: Any?, // null
                @Json(name = "icon_color")
                val iconColor: String?,
                @Json(name = "text_color")
                val textColor: String?,
                @Json(name = "title")
                val title: String? // + 5 تا %10 تخفیف در بانی‌کلاب
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
                val bottomDescription: String?, // <p>&nbsp;</p><p style="text-align: right;">نکته:</p><ol><li style="text-align: right;">یک پا از پای دیگر بزرگتر است؛ سعی کنید معیار اندازه گیریتان پای بزرگتر باشد.</li><li style="text-align: right;">بهتر است پا را در ساعات پایانی روز اندازه بگیرید؛ زیرا درآن زمان در بزرگترین حالت خود قرار دارد.</li></ol><p>&nbsp;</p>
                @Json(name = "image")
                val image: String?, // https://www.banimode.com/img/cms/991129/1613556086.jpg
                @Json(name = "top_description")
                val topDescription: String? // <ol><li style="text-align: right;">بر روی یک سطح صاف مطابق شکل یک کاغذ قرار دهید و روی آن بایستید.</li><li style="text-align: right;">تصویری از دور پای خود روی کاغذ بکشید.</li><li style="text-align: right;">با استفاده از متر یا خط کش، در شکل ترسیم شده طول را از انتهای پاشنه تا بلندترین انگشت اندازه بگیرید.</li><li style="text-align: right;">اندازه به دست آمده را با جدول راهنمای سایز مطابقت دهید.</li></ol>
            )

            @JsonClass(generateAdapter = true)
            data class ProductSpecificPrice(
                @Json(name = "discount_amount")
                val discountAmount: Int?, // 804000
                @Json(name = "discount_percent")
                val discountPercent: Int?, // 30
                @Json(name = "from")
                val from: Any?, // null
                @Json(name = "specific_price")
                val specificPrice: Int?, // 1876000
                @Json(name = "to")
                val to: Any? // null
            )

            @JsonClass(generateAdapter = true)
            data class Size(
                @Json(name = "active")
                val active: Int?, // 1
                @Json(name = "display_barcode")
                val displayBarcode: String?, // PSW3110337
                @Json(name = "extra_barcodes")
                val extraBarcodes: List<String?>?,
                @Json(name = "has_quantity")
                val hasQuantity: Int?, // 1
                @Json(name = "id_size")
                val idSize: Int?, // 113
                @Json(name = "name")
                val name: String?, // 37
                @Json(name = "position")
                val position: Int?, // 174
                @Json(name = "sellers")
                val sellers: List<Seller?>?,
                @Json(name = "show_in_filter")
                val showInFilter: Boolean?, // true
                @Json(name = "slug")
                val slug: String? // 37
            ) {
                @JsonClass(generateAdapter = true)
                data class Seller(
                    @Json(name = "barcode")
                    val barcode: String?, // PSW3110337
                    @Json(name = "base_price")
                    val basePrice: Int?, // 2680000
                    @Json(name = "final_price")
                    val finalPrice: Int?, // 1876000
                    @Json(name = "has_quantity")
                    val hasQuantity: Int?, // 1
                    @Json(name = "id_product_attribute")
                    val idProductAttribute: Int?, // 2150986
                    @Json(name = "id_seller")
                    val idSeller: Int?, // 1
                    @Json(name = "quantity")
                    val quantity: Int?, // 4
                    @Json(name = "score")
                    val score: Int?, // 5
                    @Json(name = "specific_price")
                    val specificPrice: SpecificPrice?,
                    @Json(name = "specific_price_date_from")
                    val specificPriceDateFrom: String?, // 0000-00-00 00:00:00
                    @Json(name = "specific_price_date_to")
                    val specificPriceDateTo: String? // 0000-00-00 00:00:00
                ) {
                    @JsonClass(generateAdapter = true)
                    data class SpecificPrice(
                        @Json(name = "discount_amount")
                        val discountAmount: Int?, // 804000
                        @Json(name = "discount_percent")
                        val discountPercent: Int?, // 30
                        @Json(name = "from")
                        val from: Any?, // null
                        @Json(name = "specific_price")
                        val specificPrice: Int?, // 1876000
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
        data class Links(
            @Json(name = "first")
            val first: String?, // https://mobapi.banimode.com/api/v4/products/related-category?query=%28total_qty%3A%3E0%29&page=1
            @Json(name = "last")
            val last: String?, // https://mobapi.banimode.com/api/v4/products/related-category?query=%28total_qty%3A%3E0%29&page=18
            @Json(name = "next")
            val next: String?, // https://mobapi.banimode.com/api/v4/products/related-category?query=%28total_qty%3A%3E0%29&page=2
            @Json(name = "prev")
            val prev: Any? // null
        )

        @JsonClass(generateAdapter = true)
        data class Meta(
            @Json(name = "active")
            val active: Int?, // 0
            @Json(name = "brand_logo")
            val brandLogo: String?,
            @Json(name = "category_info")
            val categoryInfo: CategoryInfo?,
            @Json(name = "category_top_menus")
            val categoryTopMenus: List<Any?>?,
            @Json(name = "current_page")
            val currentPage: Int?, // 1
            @Json(name = "default_sort")
            val defaultSort: String?, // default
            @Json(name = "default_stock")
            val defaultStock: Boolean?, // true
            @Json(name = "filters_status")
            val filtersStatus: FiltersStatus?,
            @Json(name = "footer_description")
            val footerDescription: String?,
            @Json(name = "from")
            val from: Int?, // 1
            @Json(name = "has_filter")
            val hasFilter: Boolean?, // false
            @Json(name = "has_promotion")
            val hasPromotion: Boolean?, // true
            @Json(name = "keywords")
            val keywords: String?,
            @Json(name = "last_page")
            val lastPage: Int?, // 18
            @Json(name = "link_rewrite")
            val linkRewrite: String?,
            @Json(name = "meta_description")
            val metaDescription: String?,
            @Json(name = "meta_title")
            val metaTitle: String?,
            @Json(name = "page_description")
            val pageDescription: String?,
            @Json(name = "page_title")
            val pageTitle: String?, // محصولات بانی مد
            @Json(name = "per_page")
            val perPage: Int?, // 24
            @Json(name = "seller_info")
            val sellerInfo: Any?, // null
            @Json(name = "seo_canonical")
            val seoCanonical: String?,
            @Json(name = "seo_noindex")
            val seoNoindex: Int?, // 0
            @Json(name = "seo_redirect")
            val seoRedirect: String?,
            @Json(name = "show_flash_sales")
            val showFlashSales: Boolean?, // false
            @Json(name = "sidebar_display")
            val sidebarDisplay: Int?, // 0
            @Json(name = "size_reference_exist")
            val sizeReferenceExist: List<Any?>?,
            @Json(name = "to")
            val to: Int?, // 25
            @Json(name = "total")
            val total: Int? // 429
        ) {
            @JsonClass(generateAdapter = true)
            data class CategoryInfo(
                @Json(name = "breadcrumb")
                val breadcrumb: List<Any?>?,
                @Json(name = "breadcrumb_childs")
                val breadcrumbChilds: List<Any?>?,
                @Json(name = "breadcrumb_neighbor")
                val breadcrumbNeighbor: List<Any?>?,
                @Json(name = "breadcrumb_tree")
                val breadcrumbTree: List<Any?>?,
                @Json(name = "category_id")
                val categoryId: Int?, // 0
                @Json(name = "deepest_level")
                val deepestLevel: Int? // 0
            )

            @JsonClass(generateAdapter = true)
            data class FiltersStatus(
                @Json(name = "categories")
                val categories: Boolean?, // true
                @Json(name = "colors")
                val colors: Boolean?, // true
                @Json(name = "manufacturers")
                val manufacturers: Boolean?, // true
                @Json(name = "sizes")
                val sizes: Boolean? // true
            )
        }
    }
}