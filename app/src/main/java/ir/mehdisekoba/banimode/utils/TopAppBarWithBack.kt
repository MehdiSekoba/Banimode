package ir.mehdisekoba.banimode.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ir.mehdisekoba.banimode.R
import ir.mehdisekoba.banimode.screens.theme.Cultured
import ir.mehdisekoba.banimode.screens.theme.Eerie_Black
import ir.mehdisekoba.banimode.screens.theme.Spanish_Crimson
import ir.mehdisekoba.banimode.screens.theme.White

@Composable
fun TopAppBarWithBack(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier.background(White)
            .fillMaxWidth()
            .padding(top = 32.dp, start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(40.dp)
                .background(Cultured, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right_3),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }


        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 14.dp),
            style = MaterialTheme.typography.titleMedium.copy(

                color =Eerie_Black
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        // Like button
        IconButton(
            onClick = { },
            modifier = Modifier
                .size(40.dp)
                .background(Cultured, shape = CircleShape)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = null,
                tint = Spanish_Crimson,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}



