package ir.mehdisekoba.banimode.data.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDetail(
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String?, // Get product #102550.
    @Json(name = "status")
    val status: String?, // success
    @Json(name = "status_code")
    val statusCode: Int? // 200
) {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "colors")
        val colors: List<Color>?,
        @Json(name = "product")
        val product: Product?
    ) {
        @JsonClass(generateAdapter = true)
        data class Color(
            @Json(name = "cash_back")
            val cashBack: CashBack?,
            @Json(name = "color_name")
            val colorName: String?, // مشکی آبی
            @Json(name = "color_reference")
            val colorReference: String?, // 102550-573
            @Json(name = "color_slug")
            val colorSlug: String?, // مشکی_آبی
            @Json(name = "color_value")
            val colorValue: String?, // #002968
            @Json(name = "customer_club_coin")
            val customerClubCoin: Int?, // 547
            @Json(name = "has_product_set")
            val hasProductSet: Int?, // 0
            @Json(name = "has_related_category")
            val hasRelatedCategory: Int?, // 1
            @Json(name = "id_color")
            val idColor: Int?, // 573
            @Json(name = "id_product_attribute")
            val idProductAttribute: Int?, // 0
            @Json(name = "images")
            val images: Images?,
            @Json(name = "link")
            val link: String?, // BM-102550/کیف-دوشی-چرم-طبیعی-زنانه-منط-mante-کد-f118sh.html#/رنگ-مشکی_آبی
            @Json(name = "new_images")
            val newImages: List<NewImage>?,
            @Json(name = "new_link")
            val newLink: String?, // BM-102550/کیف-دوشی-چرم-طبیعی-زنانه-منط-mante-کد-f118sh.html?color=رنگ-مشکی_آبی
            @Json(name = "product_base_price")
            val productBasePrice: Int?, // 13698000
            @Json(name = "product_final_price")
            val productFinalPrice: Int?, // 5479200
            @Json(name = "product_price")
            val productPrice: Int?, // 13698000
            @Json(name = "product_sets")
            val productSets: List<Any>?,
            @Json(name = "product_specific_price")
            val productSpecificPrice: ProductSpecificPrice?,
            @Json(name = "reference")
            val reference: String?,
            @Json(name = "related_category")
            val relatedCategory: List<String>?,
            @Json(name = "sellers")
            val sellers: List<Seller>?,
            @Json(name = "size")
            val size: List<Size>?,
            @Json(name = "total_qty")
            val totalQty: Int?, // 1
            @Json(name = "videos")
            val videos: List<Any>?
        ) {
            @JsonClass(generateAdapter = true)
            data class CashBack(
                @Json(name = "background_color")
                val backgroundColor: String?, // #ffe3d3
                @Json(name = "cash_back_price")
                val cashBackPrice: Int?, // 219168
                @Json(name = "description")
                val description: String?, // 4% شارژ کیف پول
                @Json(name = "percent")
                val percent: Int?, // 4
                @Json(name = "product_price")
                val productPrice: Int?, // 5479200
                @Json(name = "text_color")
                val textColor: String? // #f16422
            )

            @JsonClass(generateAdapter = true)
            data class Images(
                @Json(name = "cart_default")
                val cartDefault: List<String>?,
                @Json(name = "home_default")
                val homeDefault: List<String>?,
                @Json(name = "large_default")
                val largeDefault: List<String>?,
                @Json(name = "medium_default")
                val mediumDefault: List<String>?,
                @Json(name = "small_default")
                val smallDefault: List<String>?,
                @Json(name = "thickbox_default")
                val thickboxDefault: List<String>?,
                @Json(name = "thickbox_default2x")
                val thickboxDefault2x: List<String>?,
                @Json(name = "zoom")
                val zoom: List<String>?
            )

            @JsonClass(generateAdapter = true)
            data class NewImage(
                @Json(name = "alt")
                val alt: String?, // کیف دوشی چرم طبیعی زنانه منط Mante کد F118Sh
                @Json(name = "image_size")
                val imageSize: ImageSize?,
                @Json(name = "title")
                val title: String? // کیف دوشی چرم طبیعی زنانه منط Mante کد F118Sh
            ) {
                @JsonClass(generateAdapter = true)
                data class ImageSize(
                    @Json(name = "cart_default")
                    val cartDefault: String?, // https://www.banimode.com/1102128-cart_default/102550.jpg
                    @Json(name = "home_default")
                    val homeDefault: String?, // https://www.banimode.com/1102128-home_default/102550.jpg
                    @Json(name = "large_default")
                    val largeDefault: String?, // https://www.banimode.com/1102128-large_default/102550.jpg
                    @Json(name = "medium_default")
                    val mediumDefault: String?, // https://www.banimode.com/1102128-medium_default/102550.jpg
                    @Json(name = "small_default")
                    val smallDefault: String?, // https://www.banimode.com/1102128-small_default/102550.jpg
                    @Json(name = "thickbox_default")
                    val thickboxDefault: String?, // https://www.banimode.com/1102128-thickbox_default/102550.jpg
                    @Json(name = "thickbox_default2x")
                    val thickboxDefault2x: String?, // https://www.banimode.com/1102128-thickbox_default2x/102550.jpg
                    @Json(name = "zoom")
                    val zoom: String? // https://www.banimode.com/1102128/102550.jpg
                )
            }

            @JsonClass(generateAdapter = true)
            data class ProductSpecificPrice(
                @Json(name = "discount_amount")
                val discountAmount: Int?, // 8218800
                @Json(name = "discount_percent")
                val discountPercent: Int?, // 60
                @Json(name = "from")
                val from: Any?, // null
                @Json(name = "specific_price")
                val specificPrice: Int?, // 5479200
                @Json(name = "to")
                val to: Any? // null
            )

            @JsonClass(generateAdapter = true)
            data class Seller(
                @Json(name = "description")
                val description: String?, // یک توصیفی در وصف برند
                @Json(name = "id_seller")
                val idSeller: Int?, // 1
                @Json(name = "logo")
                val logo: String?, // https://www.banimode.com/img/seller/1-1707138125.png
                @Json(name = "name")
                val name: String?, // بانی مد
                @Json(name = "ready_for_order")
                val readyForOrder: Int?, // 1
                @Json(name = "score")
                val score: Int?, // 5
                @Json(name = "score_new")
                val scoreNew: ScoreNew?,
                @Json(name = "shipping_methods")
                val shippingMethods: List<ShippingMethod>?,
                @Json(name = "slug")
                val slug: String? // Banimode
            ) {
                @JsonClass(generateAdapter = true)
                data class ScoreNew(
                    @Json(name = "color")
                    val color: String?, // #1AC977
                    @Json(name = "group")
                    val group: String?, // D
                    @Json(name = "score")
                    val score: Int?, // 5
                    @Json(name = "score_text")
                    val scoreText: String?, // 5 از 5
                    @Json(name = "text")
                    val text: String? // عالی
                )

                @JsonClass(generateAdapter = true)
                data class ShippingMethod(
                    @Json(name = "description")
                    val description: String?, // ویژه تهران و کرج و مشهد
                    @Json(name = "icon")
                    val icon: String?, // https://www.banimode.com/img/s/1.jpg
                    @Json(name = "minimum_delivery_time")
                    val minimumDeliveryTime: Int?, // 3
                    @Json(name = "shipping_methods")
                    val shippingMethods: String? // اکسپرس بانی مد
                )
            }

            @JsonClass(generateAdapter = true)
            data class Size(
                @Json(name = "active")
                val active: Int?, // 1
                @Json(name = "display_barcode")
                val displayBarcode: String?, // 0401182512840106262100
                @Json(name = "extra_barcodes")
                val extraBarcodes: List<Any>?,
                @Json(name = "has_quantity")
                val hasQuantity: Int?, // 1
                @Json(name = "id_size")
                val idSize: Int?, // 571
                @Json(name = "name")
                val name: String?, // تک سایز
                @Json(name = "position")
                val position: Int?, // 600
                @Json(name = "sellers")
                val sellers: List<Seller>?,
                @Json(name = "show_in_filter")
                val showInFilter: Boolean?, // true
                @Json(name = "slug")
                val slug: String? // تک_سایز
            ) {
                @JsonClass(generateAdapter = true)
                data class Seller(
                    @Json(name = "barcode")
                    val barcode: String?, // 0401182512840106262100
                    @Json(name = "base_price")
                    val basePrice: Int?, // 13698000
                    @Json(name = "final_price")
                    val finalPrice: Int?, // 5479200
                    @Json(name = "has_quantity")
                    val hasQuantity: Int?, // 1
                    @Json(name = "id_product_attribute")
                    val idProductAttribute: Int?, // 680636
                    @Json(name = "id_seller")
                    val idSeller: Int?, // 1
                    @Json(name = "quantity")
                    val quantity: Int?, // 1
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
                        val discountAmount: Int?, // 8218800
                        @Json(name = "discount_percent")
                        val discountPercent: Int?, // 60
                        @Json(name = "from")
                        val from: Any?, // null
                        @Json(name = "specific_price")
                        val specificPrice: Int?, // 5479200
                        @Json(name = "to")
                        val to: Any? // null
                    )
                }
            }
        }

        @JsonClass(generateAdapter = true)
        data class Product(
            @Json(name = "all_colors")
            val allColors: List<Any>?,
            @Json(name = "all_colors_pwa")
            val allColorsPwa: List<AllColorsPwa>?,
            @Json(name = "ar_type")
            val arType: Any?, // null
            @Json(name = "available_notify")
            val availableNotify: Boolean?, // true
            @Json(name = "banijet_tag")
            val banijetTag: Any?, // null
            @Json(name = "body_type")
            val bodyType: String?, // none
            @Json(name = "breadcrumbs")
            val breadcrumbs: List<Breadcrumb>?,
            @Json(name = "club_label")
            val clubLabel: ClubLabel?,
            @Json(name = "has_ar")
            val hasAr: Boolean?, // false
            @Json(name = "id_product")
            val idProduct: Int?, // 102550
            @Json(name = "new_size_chart_support")
            val newSizeChartSupport: Boolean?, // false
            @Json(name = "product_category_default_id")
            val productCategoryDefaultId: Int?, // 435
            @Json(name = "product_category_default_name_en")
            val productCategoryDefaultNameEn: String?, // کیف دوشی زنانه
            @Json(name = "product_category_size_guide")
            val productCategorySizeGuide: Any?, // null
            @Json(name = "product_description")
            val productDescription: String?,
            @Json(name = "product_description_short")
            val productDescriptionShort: String?, // <div><div><a href="https://www.banimode.com/content/145/mante-guarantee" target="_blank"><strong>جهت مشاهده شرایط گارانتی محصولات چرم منط کلیک نمائید.</strong></a></div></div><ul class="custom-style-line"><li><strong>تعداد جیب داخلی:</strong> دو عدد جیب زیپ دار</li><li style="text-align:justify;"><strong>ابعاد کیف:</strong> 6×13×26 سانتی متر</li><li style="text-align:justify;"><strong>ارتفاع بند دوشی در بلندترین حالت: </strong>42 سانتی متر</li><li style="text-align:justify;"><strong>تعداد محفظه اصلی:</strong> 1 عدد</li><li style="text-align:justify;"><strong>جزئیات مدل:</strong> ساخته شده از چرم طبیعی، یراق آلات و زنجیر فلزی رنگ ثابت، دارای 1 عدد جاکارتی</li></ul>
            @Json(name = "product_features")
            val productFeatures: List<ProductFeature>?,
            @Json(name = "product_link_rewrite")
            val productLinkRewrite: String?, // کیف-دوشی-چرم-طبیعی-زنانه-منط-mante-کد-f118sh
            @Json(name = "product_manufacturer_en_name")
            val productManufacturerEnName: String?, // Mante
            @Json(name = "product_manufacturer_id")
            val productManufacturerId: Int?, // 995
            @Json(name = "product_manufacturer_logo")
            val productManufacturerLogo: String?, // https://www.banimode.com/img/m/995-brand-136x69.jpg
            @Json(name = "product_manufacturer_name")
            val productManufacturerName: String?, // منط
            @Json(name = "product_manufacturer_slug")
            val productManufacturerSlug: String?, // منط
            @Json(name = "product_manufacturer_title")
            val productManufacturerTitle: String?, // خرید محصولات برند منط (Mante) با بهترین قیمت
            @Json(name = "product_meta_description")
            val productMetaDescription: String?, // کیف دوشی چرم طبیعی زنانه منط مدل F118Sh-رنگ :مشکی آبی-جنس:چرم طبیعی-نوع چرم:گاوی-طرح:کروکو-برند:منط-کشور سازنده:ایران-بانی مد 
            @Json(name = "product_meta_keywords")
            val productMetaKeywords: String?,
            @Json(name = "product_meta_title")
            val productMetaTitle: String?, // کیف دوشی چرم طبیعی زنانه منط مدل F118Sh|رنگ مشکی آبی-بانی مد
            @Json(name = "product_name")
            val productName: String?, // کیف دوشی چرم طبیعی زنانه منط مدل F118Sh
            @Json(name = "product_recommendation")
            val productRecommendation: Any?, // null
            @Json(name = "product_reference")
            val productReference: String?, // AM-MANTE-F118SH
            @Json(name = "product_score")
            val productScore: Any?, // null
            @Json(name = "product_size_guide")
            val productSizeGuide: Any?, // null
            @Json(name = "promotion_label")
            val promotionLabel: Any?, // null
            @Json(name = "seo_canonical")
            val seoCanonical: String?,
            @Json(name = "size_chart_support")
            val sizeChartSupport: Boolean?, // false
            @Json(name = "view_type")
            val viewType: ViewType?
        ) {
            @JsonClass(generateAdapter = true)
            data class AllColorsPwa(
                @Json(name = "id")
                val id: Int?, // 573
                @Json(name = "name")
                val name: String?, // مشکی آبی
                @Json(name = "sizes")
                val sizes: List<Size>?,
                @Json(name = "slug")
                val slug: String?, // مشکی_آبی
                @Json(name = "value")
                val value: String? // #002968
            ) {
                @JsonClass(generateAdapter = true)
                data class Size(
                    @Json(name = "id_size")
                    val idSize: Int?, // 571
                    @Json(name = "name")
                    val name: String?, // تک سایز
                    @Json(name = "slug")
                    val slug: String? // تک_سایز
                )
            }

            @JsonClass(generateAdapter = true)
            data class Breadcrumb(
                @Json(name = "id")
                val id: Int?, // 2
                @Json(name = "link")
                val link: String?, // همه-محصولات
                @Json(name = "name")
                val name: String? // همه محصولات
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
                val title: String? // + 5 تا %15 تخفیف در بانی‌کلاب
            )

            @JsonClass(generateAdapter = true)
            data class ProductFeature(
                @Json(name = "title")
                val title: String?, // جنس
                @Json(name = "value")
                val value: String? // چرم طبیعی
            )

            @JsonClass(generateAdapter = true)
            data class ViewType(
                @Json(name = "color_title")
                val colorTitle: String?, // رنگ
                @Json(name = "size_title")
                val sizeTitle: String? // سایز
            )
        }
    }
}