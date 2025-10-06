package pe.edu.upc.easymovie.features.favorites.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.edu.upc.easymovie.features.movies.domain.Movie


@Composable
fun FavoriteCard(
    movie: Movie,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row {
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier
                    .height(96.dp)
                    .width(56.dp),
                contentScale = ContentScale.FillHeight
            )
            Column(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    movie.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    movie.overview,
                    maxLines = 2,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            IconButton(
                onClick = onToggleFavorite
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}