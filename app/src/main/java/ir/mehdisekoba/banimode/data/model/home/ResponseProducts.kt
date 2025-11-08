package ir.mehdisekoba.banimode.data.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseProducts(
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
        val `data`: List<Data>?,
        @Json(name = "filters")
        val filters: List<Any?>?,
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
            val colorLink: String?, // BM-474220/بالشت-طبی-موج-دار-آدور-کد-41008/1.html#/رنگ-سفید
            @Json(name = "color_link_new")
            val colorLinkNew: String?, // BM-474220/بالشت-طبی-موج-دار-آدور-کد-41008/1.html#/رنگ-سفید
            @Json(name = "color_name")
            val colorName: String?, // سفید
            @Json(name = "color_slug")
            val colorSlug: String?, // سفید
            @Json(name = "color_value")
            val colorValue: String?, // #ffffff
            @Json(name = "flash_sale_message")
            val flashSaleMessage: List<Any?>?,
            @Json(name = "flash_sale_message_show")
            val flashSaleMessageShow: Boolean?, // false
            @Json(name = "id_color")
            val idColor: Int?, // 1
            @Json(name = "id_product")
            val idProduct: Int?, // 474220
            @Json(name = "id_product_attribute")
            val idProductAttribute: Int?, // 2189404
            @Json(name = "images")
            val images: Images?,
            @Json(name = "link")
            val link: String?, // BM-474220/بالشت-طبی-موج-دار-آدور-کد-41008.html#/رنگ-سفید
            @Json(name = "product_category_default_name_en")
            val productCategoryDefaultNameEn: String?, // ارتوپدی و توانبخشی
            @Json(name = "product_category_size_guide")
            val productCategorySizeGuide: Any?, // null
            @Json(name = "product_manufacturer_en_name")
            val productManufacturerEnName: String?, // Ador
            @Json(name = "product_manufacturer_id")
            val productManufacturerId: Int?, // 3766
            @Json(name = "product_manufacturer_name")
            val productManufacturerName: String?, // آدور
            @Json(name = "product_manufacturer_slug")
            val productManufacturerSlug: String?, // آدور
            @Json(name = "product_name")
            val productName: String?, // بالشت طبی موج دار آدور کد 41008
            @Json(name = "product_price")
            val productPrice: Int?, // 1998000
            @Json(name = "product_reference")
            val productReference: String?, // AM-ADO-41008-2
            @Json(name = "product_size_guide")
            val productSizeGuide: Any?, // null
            @Json(name = "product_specific_price")
            val productSpecificPrice: ProductSpecificPrice?,
            @Json(name = "promotion_label")
            val promotionLabel: Any?, // null
            @Json(name = "size")
            val size: List<Size?>?,
            @Json(name = "total_qty")
            val totalQty: Int?, // 2
            @Json(name = "videos")
            val videos: List<Any?>?
        ) {
            @JsonClass(generateAdapter = true)
            data class AllColorsPwa(
                @Json(name = "name")
                val name: String?, // سفید
                @Json(name = "slug")
                val slug: String?, // سفید
                @Json(name = "value")
                val value: String? // #ffffff
            )

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
            data class ProductSpecificPrice(
                @Json(name = "discount_amount")
                val discountAmount: Int?, // 61200
                @Json(name = "discount_percent")
                val discountPercent: Int?, // 35
                @Json(name = "from")
                val from: Any?, // null
                @Json(name = "specific_price")
                val specificPrice: Int?, // 113700
                @Json(name = "to")
                val to: Any? // null
            )

            @JsonClass(generateAdapter = true)
            data class Size(
                @Json(name = "active")
                val active: Int?, // 1
                @Json(name = "extra_barcodes")
                val extraBarcodes: List<Any?>?,
                @Json(name = "id_product_attribute")
                val idProductAttribute: Int?, // 2189404
                @Json(name = "id_seller")
                val idSeller: Int?, // 1
                @Json(name = "id_size")
                val idSize: Int?, // 2
                @Json(name = "name")
                val name: String?, // XL
                @Json(name = "position")
                val position: Int?, // 7
                @Json(name = "quantity")
                val quantity: Int?, // 2
                @Json(name = "reference")
                val reference: String?, // 410084
                @Json(name = "slug")
                val slug: String?, // XL
                @Json(name = "specific_price")
                val specificPrice: SpecificPrice?
            ) {
                @JsonClass(generateAdapter = true)
                data class SpecificPrice(
                    @Json(name = "discount_amount")
                    val discountAmount: Int?, // 61200
                    @Json(name = "discount_percent")
                    val discountPercent: Int?, // 35
                    @Json(name = "from")
                    val from: Any?, // null
                    @Json(name = "specific_price")
                    val specificPrice: Int?, // 113700
                    @Json(name = "to")
                    val to: Any? // null
                )
            }
        }

        @JsonClass(generateAdapter = true)
        data class Links(
            @Json(name = "first")
            val first: String?, // https://mobapi.banimode.com/api/v1/products?query=%28product_categories.id%3A183%29%20AND%20%28total_qty%3A%3E0%29&page=1
            @Json(name = "last")
            val last: String?, // https://mobapi.banimode.com/api/v1/products?query=%28product_categories.id%3A183%29%20AND%20%28total_qty%3A%3E0%29&page=507
            @Json(name = "next")
            val next: String?, // https://mobapi.banimode.com/api/v1/products?query=%28product_categories.id%3A183%29%20AND%20%28total_qty%3A%3E0%29&page=2
            @Json(name = "prev")
            val prev: Any? // null
        )

        @JsonClass(generateAdapter = true)
        data class Meta(
            @Json(name = "active")
            val active: Int?, // 1
            @Json(name = "brand_logo")
            val brandLogo: String?,
            @Json(name = "category_info")
            val categoryInfo: CategoryInfo?,
            @Json(name = "category_top_menus")
            val categoryTopMenus: List<CategoryTopMenu?>?,
            @Json(name = "current_page")
            val currentPage: Int?, // 1
            @Json(name = "default_sort")
            val defaultSort: String?, // default
            @Json(name = "default_stock")
            val defaultStock: Boolean?, // true
            @Json(name = "filters_status")
            val filtersStatus: FiltersStatus?,
            @Json(name = "footer_description")
            val footerDescription: String?, // <p>محصولات سلامت و زیبایی یا آرایش و بهداشتی ارائه شده در بانی مد از ضمانت اصالت برخوردارند. این محصولات شامل لوازم آرایش صورت، چشم و لب، لوازم بهداشتی بدن، محصولات آرایش و زیبایی ناخن از محبوب ترین برندهای دنیا به صورت آنلاین برای مشتریان بانی مد قابل دسترس اند.</p>
            @Json(name = "from")
            val from: Int?, // 1
            @Json(name = "has_filter")
            val hasFilter: Boolean?, // false
            @Json(name = "has_promotion")
            val hasPromotion: Boolean?, // false
            @Json(name = "keywords")
            val keywords: String?,
            @Json(name = "last_page")
            val lastPage: Int?, // 507
            @Json(name = "link_rewrite")
            val linkRewrite: String?, // category-health-beauty
            @Json(name = "meta_description")
            val metaDescription: String?, // خرید انواع محصولات سلامت و زیبایی، عطر، ادوپرفیوم، ادوتویلت، پرفیوم و لوازم آرایشی و بهداشتی انواع کرم ها، انواع لوازم آرایشی با ضمانت اصالت کالا در بانی مد.
            @Json(name = "meta_title")
            val metaTitle: String?, // محصولات سلامت و زیبایی | خرید آنلاین
            @Json(name = "page_description")
            val pageDescription: String?,
            @Json(name = "page_title")
            val pageTitle: String?, // سلامت و زیبایی
            @Json(name = "per_page")
            val perPage: Int?, // 10
            @Json(name = "seo_canonical")
            val seoCanonical: String?,
            @Json(name = "seo_noindex")
            val seoNoindex: Int?, // 0
            @Json(name = "seo_redirect")
            val seoRedirect: String?,
            @Json(name = "show_flash_sales")
            val showFlashSales: Boolean?, // true
            @Json(name = "sidebar_display")
            val sidebarDisplay: Int?, // 1
            @Json(name = "size_reference_exist")
            val sizeReferenceExist: List<Any?>?,
            @Json(name = "to")
            val to: Int?, // 11
            @Json(name = "total")
            val total: Int? // 5067
        ) {
            @JsonClass(generateAdapter = true)
            data class CategoryInfo(
                @Json(name = "breadcrumb")
                val breadcrumb: List<Breadcrumb?>?,
                @Json(name = "breadcrumb_childs")
                val breadcrumbChilds: List<BreadcrumbChild?>?,
                @Json(name = "breadcrumb_neighbor")
                val breadcrumbNeighbor: List<Any?>?,
                @Json(name = "breadcrumb_tree")
                val breadcrumbTree: List<BreadcrumbTree?>?,
                @Json(name = "category_id")
                val categoryId: Int?, // 183
                @Json(name = "deepest_level")
                val deepestLevel: Int? // 4
            ) {
                @JsonClass(generateAdapter = true)
                data class Breadcrumb(
                    @Json(name = "id")
                    val id: Int?, // 183
                    @Json(name = "level")
                    val level: Int?, // 2
                    @Json(name = "link_rewrite")
                    val linkRewrite: String?, // category-health-beauty
                    @Json(name = "name")
                    val name: String?, // سلامت و زیبایی
                    @Json(name = "parent_id")
                    val parentId: Int?, // 2
                    @Json(name = "position")
                    val position: Int? // 1
                )

                @JsonClass(generateAdapter = true)
                data class BreadcrumbChild(
                    @Json(name = "id")
                    val id: Int?, // 259
                    @Json(name = "level")
                    val level: Int?, // 3
                    @Json(name = "link_rewrite")
                    val linkRewrite: String?, // category-perfume-all
                    @Json(name = "name")
                    val name: String?, // عطر و ادکلن
                    @Json(name = "parent_id")
                    val parentId: Int?, // 183
                    @Json(name = "position")
                    val position: Int? // 0
                )

                @JsonClass(generateAdapter = true)
                data class BreadcrumbTree(
                    @Json(name = "id")
                    val id: Int?, // 183
                    @Json(name = "items")
                    val items: List<Item?>?,
                    @Json(name = "level")
                    val level: Int?, // 2
                    @Json(name = "link_rewrite")
                    val linkRewrite: String?, // category-health-beauty
                    @Json(name = "name")
                    val name: String?, // سلامت و زیبایی
                    @Json(name = "parent_id")
                    val parentId: Int?, // 2
                    @Json(name = "position")
                    val position: Int? // 1
                ) {
                    @JsonClass(generateAdapter = true)
                    data class Item(
                        @Json(name = "id")
                        val id: Int?, // 259
                        @Json(name = "items")
                        val items: List<Item?>?,
                        @Json(name = "level")
                        val level: Int?, // 3
                        @Json(name = "link_rewrite")
                        val linkRewrite: String?, // category-perfume-all
                        @Json(name = "name")
                        val name: String?, // عطر و ادکلن
                        @Json(name = "parent_id")
                        val parentId: Int?, // 183
                        @Json(name = "position")
                        val position: Int? // 0
                    ) {
                        @JsonClass(generateAdapter = true)
                        data class Item(
                            @Json(name = "id")
                            val id: Int?, // 184
                            @Json(name = "level")
                            val level: Int?, // 4
                            @Json(name = "link_rewrite")
                            val linkRewrite: String?, // category-women-perfume
                            @Json(name = "name")
                            val name: String?, // عطر و ادکلن زنانه
                            @Json(name = "parent_id")
                            val parentId: Int?, // 259
                            @Json(name = "position")
                            val position: Int? // 0
                        )
                    }
                }
            }

            @JsonClass(generateAdapter = true)
            data class CategoryTopMenu(
                @Json(name = "image")
                val image: String?, // https://www.banimode.com/img/cms/030229/1716033153.jpg
                @Json(name = "link")
                val link: String?, // /259/category-perfume-all
                @Json(name = "title")
                val title: String? // عطر و ادکلن
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