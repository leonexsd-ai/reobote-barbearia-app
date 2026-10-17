package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.OnNeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark

@Composable
fun ServiceCard(
    name: String,
    price: String,
    duration: String,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    isSelected: Boolean = false,
    onSelectToggle: ((Boolean) -> Unit)? = null,
) {
    val cardBorder = if (isSelected) {
        BorderStroke(1.5.dp, NeonGreen)
    } else {
        null
    }

    ReoboteCard(
        modifier = modifier.clickable {
            onSelectToggle?.invoke(!isSelected)
        },
        border = cardBorder,
        contentPadding = 12.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Service Image / Placeholder
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(ReoboteTheme.shapes.medium)
                    .background(SurfaceContainerDark),
                contentAlignment = Alignment.Center,
            ) {
                if (!imageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize(),
                    )
                } else {
                    Text(
                        text = name.take(2).uppercase(),
                        style = ReoboteTheme.typography.titleMedium,
                        color = NeonGreen,
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Info Column
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = name,
                    style = ReoboteTheme.typography.titleMedium,
                    color = Platinum100,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = duration,
                    style = ReoboteTheme.typography.bodySmall,
                    color = Platinum500,
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = price,
                    style = ReoboteTheme.typography.titleSmall,
                    color = NeonGreen,
                )
            }

            // Selection Checkbox
            Checkbox(
                checked = isSelected,
                onCheckedChange = { checked ->
                    onSelectToggle?.invoke(checked)
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = NeonGreen,
                    uncheckedColor = Platinum500,
                    checkmarkColor = OnNeonGreen,
                ),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun ServiceCardPreview() {
    ReoboteTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            ServiceCard(
                name = "Corte Degradê Navalhado",
                price = "R$ 45,00",
                duration = "45 min",
                isSelected = true,
                onSelectToggle = {},
            )

            Spacer(modifier = Modifier.height(12.dp))

            ServiceCard(
                name = "Barba Completa & Toalha Quente",
                price = "R$ 35,00",
                duration = "30 min",
                isSelected = false,
                onSelectToggle = {},
            )
        }
    }
}
