package pe.edu.upc.easyshop.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.easyshop.shared.models.Product

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(modifier = Modifier.padding(8.dp), onClick = onClick) {

        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
            }
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier.height(192.dp)
            )

            Text(
                product.name,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "$ ${product.price}",
                fontWeight = FontWeight.SemiBold
            )
        }

    }

}
