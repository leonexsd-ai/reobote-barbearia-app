package br.com.reobote.barbearia.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.reobote.barbearia.presentation.theme.BorderOutline
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.OnNeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum600
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark

enum class TimeSlotState {
    Enabled,
    Selected,
    Disabled,
}

@Composable
fun TimeSlotChip(
    time: String,
    modifier: Modifier = Modifier,
    state: TimeSlotState = TimeSlotState.Enabled,
    onClick: (() -> Unit)? = null,
) {
    val chipShape = ReoboteTheme.shapes.medium

    val containerColor = when (state) {
        TimeSlotState.Selected -> NeonGreen
        TimeSlotState.Enabled -> SurfaceVariantDark
        TimeSlotState.Disabled -> SurfaceContainerDark.copy(alpha = 0.4f)
    }

    val contentColor = when (state) {
        TimeSlotState.Selected -> OnNeonGreen
        TimeSlotState.Enabled -> Platinum100
        TimeSlotState.Disabled -> Platinum600
    }

    val border = when (state) {
        TimeSlotState.Selected -> BorderStroke(1.5.dp, NeonGreen)
        TimeSlotState.Enabled -> BorderStroke(1.dp, BorderOutline)
        TimeSlotState.Disabled -> BorderStroke(1.dp, BorderOutline.copy(alpha = 0.2f))
    }

    Surface(
        onClick = {
            if (state != TimeSlotState.Disabled) {
                onClick?.invoke()
            }
        },
        enabled = state != TimeSlotState.Disabled,
        modifier = modifier,
        shape = chipShape,
        color = containerColor,
        contentColor = contentColor,
        border = border,
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = time,
                style = ReoboteTheme.typography.labelLarge,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun TimeSlotChipPreview() {
    ReoboteTheme {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(10.dp),
        ) {
            TimeSlotChip(
                time = "09:00",
                state = TimeSlotState.Selected,
                onClick = {},
            )

            TimeSlotChip(
                time = "10:00",
                state = TimeSlotState.Enabled,
                onClick = {},
            )

            TimeSlotChip(
                time = "11:00",
                state = TimeSlotState.Disabled,
                onClick = {},
            )
        }
    }
}
