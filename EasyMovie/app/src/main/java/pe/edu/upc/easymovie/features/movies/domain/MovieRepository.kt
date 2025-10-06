package pe.edu.upc.easymovie.features.movies.domain

interface MovieRepository {
    suspend fun getAllMovies(): List<Movie>

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)
}