package ir.mehdisekoba.banimode.data.model.home

data class AmazingUiModel(
    val id: Int,
    val title: String,
    val image: String,
    val discountPercent: Int,
    val oldPrice: Int,
    val newPrice: Int,
    val remainingQty: Int,
    val remainingTimeMillis: Int
)


