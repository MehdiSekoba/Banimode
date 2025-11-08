package ir.mehdisekoba.banimode.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.mehdisekoba.banimode.R


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    val items = stringArrayResource(id = R.array.menu_items)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .background(Color(0xFF00C853)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.Join_community),
                    modifier = Modifier.padding(top = 26.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.discounts),
                    color = Color.White.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = onLoginClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFF00E676), Color(0xFF00C853))
                                ),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.Login_Register),
                            color = Color.White,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }

        // Notification Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick("") }
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(stringResource(R.string.message), fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Icon(Icons.Outlined.Notifications, contentDescription = null, tint = Color(0xFF00C853))

        }

        HorizontalDivider(color = Color(0xFFE0E0E0))

        // Menu Items
        items.forEach { title ->
            ProfileItem(title = title, onClick = { onItemClick(title) })
        }
    }
}


@Composable
private fun ProfileItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.labelMedium
        )
        Icon(Icons.Outlined.ArrowBackIosNew, contentDescription = null, tint = Color.Gray)

    }
    HorizontalDivider(color = Color(0xFFE0E0E0))
}
