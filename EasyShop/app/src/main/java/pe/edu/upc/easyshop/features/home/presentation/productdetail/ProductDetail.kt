package pe.edu.upc.easyshop.features.home.presentation.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.easyshop.core.ui.components.RoundedIcon

@Composable
fun ProductDetail(viewModel: ProductDetailViewModel) {

    val product by viewModel.product.collectAsState()

    product?.let { product ->
        Scaffold(
            floatingActionButton = {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f)


                    ) {
                        Text("Add to cart")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f)
                    ) { Text("Shop now") }
                }
            },
            floatingActionButtonPosition = FabPosition.Center

        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {

                Box(contentAlignment = Alignment.TopEnd) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(256.dp)
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                    )
                    IconButton(onClick = {
                        viewModel.toggleFavorite()
                    }, modifier = Modifier.padding(8.dp)) {
                        Icon(
                            if (product.isFavorite) {
                                Icons.Default.Favorite
                            } else {
                                Icons.Default.FavoriteBorder
                            },
                            contentDescription = null,
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.onPrimary)
                                .padding(8.dp)

                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        product.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        "$ ${product.price}",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                )
                {
                    RoundedIcon(Icons.Default.Remove)
                    Text("1", modifier = Modifier.padding(horizontal = 8.dp))
                    RoundedIcon(Icons.Default.Add)
                }


                Text(product.description, modifier = Modifier.padding(horizontal = 8.dp))

            }
        }
    }

}