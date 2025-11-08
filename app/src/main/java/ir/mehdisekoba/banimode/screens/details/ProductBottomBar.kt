package ir.mehdisekoba.banimode.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Green
import ir.mehdisekoba.banimode.utils.moneySeparatingWithoutToman

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductBottomBar(
    basePrice: Int?,
    finalPrice: Int?,
    discountPercent: Int?,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val hasDiscount = discountPercent != null && discountPercent > 0 &&
            basePrice != null && finalPrice != null &&
            basePrice > finalPrice

    Surface(
        modifier = modifier.fillMaxWidth(),
        shadowElevation = 0.dp,
        color = Color.White,
        tonalElevation = 0.dp,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = onAddToCartClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_add_basket),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End
            ) {
                if (hasDiscount) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Badge(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ) {
                            Text(
                                text = "${discountPercent}٪",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                        Text(
                            text = basePrice.moneySeparatingWithoutToman(),
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }

                Text(
                    text = finalPrice?.moneySeparatingWithoutToman() ?: stringResource(R.string.non_existent),
                    style = MaterialTheme.typography.labelMedium,
                    color = Eerie_Black,

                )
            }

        }
    }
}