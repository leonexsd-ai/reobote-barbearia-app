package br.com.reobote.barbearia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.reobote.barbearia.presentation.components.ReoboteButton
import br.com.reobote.barbearia.presentation.components.ReoboteButtonVariant
import br.com.reobote.barbearia.presentation.components.ReoboteCard
import br.com.reobote.barbearia.presentation.theme.DarkGreenContainer
import br.com.reobote.barbearia.presentation.theme.NeonGreen
import br.com.reobote.barbearia.presentation.theme.Platinum100
import br.com.reobote.barbearia.presentation.theme.Platinum400
import br.com.reobote.barbearia.presentation.theme.Platinum500
import br.com.reobote.barbearia.presentation.theme.ReoboteTheme
import br.com.reobote.barbearia.presentation.theme.SurfaceContainerDark
import br.com.reobote.barbearia.presentation.theme.SurfaceDark
import br.com.reobote.barbearia.presentation.theme.SurfaceVariantDark
import br.com.reobote.barbearia.presentation.theme.WarningAmber

data class StoreProduct(
    val id: String,
    val name: String,
    val price: Double,
    val priceFormatted: String,
    val category: String,
    val rating: Double,
    val iconEmoji: String,
    val description: String,
)

val mockStoreProducts = listOf(
    StoreProduct("p1", "Pomada Matte Reobote 100g", 49.90, "R$ 49,90", "Cabelo", 4.9, "🧴", "Efeito fosco com alta fixação para o dia todo"),
    StoreProduct("p2", "Óleo para Barba Premium 30ml", 39.90, "R$ 39,90", "Barba", 5.0, "💧", "Hidratação intensa e brilho natural para a barba"),
    StoreProduct("p3", "Shampoo Cabelo & Barba Refresh 250ml", 45.00, "R$ 45,00", "Cabelo", 4.8, "🧼", "Limpeza profunda e sensação refrescante de mentol"),
    StoreProduct("p4", "Balm Alinhador de Barba 90g", 42.90, "R$ 42,90", "Barba", 4.9, "🪮", "Alinha os fios e combate o frizz da barba"),
    StoreProduct("p5", "Kit Grooming Completo Reobote", 129.90, "R$ 129,90", "Kits", 5.0, "🎁", "Pomada + Óleo + Shampoo + Pente de Madeira"),
    StoreProduct("p6", "Pente de Madeira Duplo Reobote", 25.00, "R$ 25,00", "Acessórios", 4.7, "🪵", "Anti-frizz natural para barba e cabelo"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen(
    modifier: Modifier = Modifier,
) {
    var selectedCategory by remember { mutableStateOf("Todos") }
    val categories = listOf("Todos", "Cabelo", "Barba", "Kits", "Acessórios")

    val cartItems = remember { mutableStateListOf<StoreProduct>() }
    var showCartSheet by remember { mutableStateOf(false) }

    val filteredProducts = if (selectedCategory == "Todos") {
        mockStoreProducts
    } else {
        mockStoreProducts.filter { it.category == selectedCategory }
    }

    if (showCartSheet) {
        ModalBottomSheet(
            onDismissRequest = { showCartSheet = false },
            sheetState = rememberModalBottomSheetState(),
            containerColor = SurfaceDark,
            contentColor = Platinum100,
        ) {
            CartSheetContent(
                cartItems = cartItems,
                onRemoveItem = { cartItems.remove(it) },
                onCheckout = {
                    cartItems.clear()
                    showCartSheet = false
                },
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = ReoboteTheme.colors.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            // Header with Cart Button Badge
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(
                        text = "Loja Reobote",
                        style = ReoboteTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                        color = Platinum100,
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Produtos profissionais para cuidar do seu estilo",
                        style = ReoboteTheme.typography.bodyMedium,
                        color = Platinum400,
                    )
                }

                // Cart Icon Button Badge
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(SurfaceVariantDark)
                        .border(1.dp, NeonGreen, CircleShape)
                        .clickable { showCartSheet = true },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = "🛒", fontSize = 20.sp)

                    if (cartItems.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(NeonGreen),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = cartItems.size.toString(),
                                style = ReoboteTheme.typography.labelSmall.copy(fontSize = 10.sp, fontWeight = FontWeight.Bold),
                                color = ReoboteTheme.colors.onPrimary,
                            )
                        }
                    }
                }
            }

            // Category Filter Chips
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(categories.size) { index ->
                    val category = categories[index]
                    val isSelected = selectedCategory == category

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (isSelected) NeonGreen else SurfaceVariantDark)
                            .clickable { selectedCategory = category }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = category,
                            style = ReoboteTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                            color = if (isSelected) ReoboteTheme.colors.onPrimary else Platinum100,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Products Grid (2 Columns)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(filteredProducts, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        onAddToCart = {
                            cartItems.add(product)
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: StoreProduct,
    onAddToCart: () -> Unit,
) {
    ReoboteCard(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = 12.dp,
    ) {
        // Image Placeholder Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .clip(ReoboteTheme.shapes.medium)
                .background(SurfaceContainerDark),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = product.iconEmoji, fontSize = 44.sp)

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .clip(CircleShape)
                    .background(DarkGreenContainer)
                    .padding(horizontal = 6.dp, vertical = 2.dp),
            ) {
                Text(
                    text = "★ ${product.rating}",
                    style = ReoboteTheme.typography.labelSmall.copy(fontSize = 10.sp),
                    color = WarningAmber,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.name,
            style = ReoboteTheme.typography.titleMedium,
            color = Platinum100,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = product.description,
            style = ReoboteTheme.typography.bodySmall.copy(fontSize = 11.sp),
            color = Platinum400,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.priceFormatted,
            style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = NeonGreen,
        )

        Spacer(modifier = Modifier.height(10.dp))

        ReoboteButton(
            text = "+ Adicionar",
            onClick = onAddToCart,
            modifier = Modifier.fillMaxWidth(),
            variant = ReoboteButtonVariant.Secondary,
        )
    }
}

@Composable
private fun CartSheetContent(
    cartItems: List<StoreProduct>,
    onRemoveItem: (StoreProduct) -> Unit,
    onCheckout: () -> Unit,
) {
    val totalPrice = cartItems.sumOf { it.price }
    val totalPriceFormatted = String.format(java.util.Locale.US, "R$ %.2f", totalPrice).replace(".", ",")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Text(
            text = "Seu Carrinho de Compras (${cartItems.size})",
            style = ReoboteTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = Platinum100,
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Seu carrinho está vazio 🛒",
                    style = ReoboteTheme.typography.bodyLarge,
                    color = Platinum400,
                )
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                cartItems.forEach { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Text(text = item.iconEmoji, fontSize = 22.sp)
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(text = item.name, style = ReoboteTheme.typography.titleMedium, color = Platinum100, maxLines = 1)
                                Text(text = item.priceFormatted, style = ReoboteTheme.typography.bodySmall, color = NeonGreen)
                            }
                        }

                        Text(
                            text = "Remover",
                            style = ReoboteTheme.typography.labelSmall,
                            color = ReoboteTheme.colors.error,
                            modifier = Modifier
                                .clip(ReoboteTheme.shapes.extraSmall)
                                .clickable { onRemoveItem(item) }
                                .padding(6.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Total Final", style = ReoboteTheme.typography.titleLarge, color = Platinum100)
                    Text(text = totalPriceFormatted, style = ReoboteTheme.typography.headlineSmall, color = NeonGreen)
                }

                Spacer(modifier = Modifier.height(16.dp))

                ReoboteButton(
                    text = "Finalizar Pedido",
                    onClick = onCheckout,
                    modifier = Modifier.fillMaxWidth(),
                    variant = ReoboteButtonVariant.Primary,
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D10)
@Composable
fun StoreScreenPreview() {
    ReoboteTheme {
        StoreScreen()
    }
}
