package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark
import br.com.reobote.barbearia.presentation.theme.WarningAmber
import java.util.Locale

@Composable
fun BarberAvatar(
    name: String,
    modifier: Modifier = Modifier,
    rating: Double = 5.0,
    imageUrl: String? = null,
    isSelected: Boolean = false,
    avatarSize: Dp = 72.dp,
    onClick: (() -> Unit)? = null,
) {
    val selectionBorder = if (isSelected) {
        Modifier.border(2.5.dp, NeonGreen, CircleShape)
    } else {
        Modifier.border(1.dp, BorderOutline, CircleShape)
    }

    Column(
        modifier = modifier
            .clip(ReoboteTheme.shapes.medium)
            .then(
                if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Circular Avatar Box
        Box(
            modifier = Modifier
                .size(avatarSize)
                .then(selectionBorder)
                .padding(3.dp)
                .clip(CircleShape)
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
                    style = ReoboteTheme.typography.titleLarge,
                    color = NeonGreen,
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Barber Name
        Text(
            text = name,
            style = ReoboteTheme.typography.labelLarge,
            color = if (isSelected) NeonGreen else Platinum100,
            maxLines = 1,
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Rating Star Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "★",
                style = ReoboteTheme.typography.labelMedium,
                color = WarningAmber,
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = String.format(Locale.US, "%.1f", rating),
                style = ReoboteTheme.typography.labelMedium,
                color = Platinum400,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun BarberAvatarPreview() {
    ReoboteTheme {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        ) {
            BarberAvatar(
                name = "Carlos Barber",
                rating = 4.9,
                isSelected = true,
                onClick = {},
            )

            BarberAvatar(
                name = "Matheus Silva",
                rating = 4.8,
                isSelected = false,
                onClick = {},
            )
        }
    }
}
