package pe.edu.upc.easymovie.features.movies.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val isFavorite: Boolean = false
)
