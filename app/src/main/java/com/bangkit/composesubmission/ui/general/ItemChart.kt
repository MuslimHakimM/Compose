package com.bangkit.composesubmission.ui.general

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkit.composesubmission.R
import com.bangkit.composesubmission.ui.theme.ComposeSubmissionTheme
import com.bangkit.composesubmission.ui.theme.Shapes

@Composable
fun ItemChart(
    motorId: Long,
    image: Int,
    name: String,
    merchCount: Int,
    onProductCountChanged: (id: Long, merchCount: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = "$name's Merchandise",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        ProductCounter(
            motorId = motorId,
            orderCount = merchCount,
            onProductIncreased = { onProductCountChanged(motorId, merchCount + 1) },
            onProductDecreased = { onProductCountChanged(motorId, merchCount - 1) },
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    ComposeSubmissionTheme {
        ItemChart(
            4, R.drawable.beat_2020, "Honda Beat", 0,
            onProductCountChanged = { motorId, isFav -> },
        )
    }
}