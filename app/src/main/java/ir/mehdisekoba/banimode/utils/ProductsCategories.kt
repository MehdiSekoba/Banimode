package ir.mehdisekoba.banimode.utils

enum class ProductsCategories(
    val id: String,
    val sortType: String,
    val sortValue: String
) {
    FASHION(CATEGORIES_FASHION_AND_CLOTHING, SORT_ASC, SORT_DEFAULT),          // مد و پوشاک
    COSMETICS(CATEGORIES_COSMETICS, SORT_DESC, SORT_SELL),                        // آرایشی و بهداشتی
    HOME_APPLIANCES(CATEGORIES_HOME_APPLIANCES, SORT_ASC, SORT_DEFAULT),                // لوازم خانه
    CHILDREN_AND_ENTERTAINMENT(
        CATEGORIES_CHILDREN_AND_ENTERTAINMENT,
        SORT_DESC,
        SORT_SELL
    ),      // کودک و سرگرمی
    SPORTS_AND_TRAVEL(CATEGORIES_SPORTS_AND_TRAVEL, SORT_DESC, SORT_SELL),               // ورزش و سفر
    DIGITAL(CATEGORIES_DIGITAL_PRODUCTS, SORT_ASC, SORT_DEFAULT),              // کالای دیجیتال
    STATIONERY(CATEGORIES_STATIONERY, SORT_DESC, SORT_SELL);                      // لوازم تحریر
}
