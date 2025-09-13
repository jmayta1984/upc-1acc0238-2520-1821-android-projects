package pe.edu.upc.easyshop.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.easyshop.core.ui.theme.AppTheme
import pe.edu.upc.easyshop.shared.models.Product
import pe.edu.upc.easyshop.shared.models.products

@Composable
fun ProductCard(product: Product) {
    Card(modifier = Modifier.padding(8.dp)) {

        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
            }
            AsyncImage(model = product.image, contentDescription = null)

            Text(
                product.name,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                "$ ${product.price}",
                fontWeight = FontWeight.SemiBold
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    AppTheme {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}