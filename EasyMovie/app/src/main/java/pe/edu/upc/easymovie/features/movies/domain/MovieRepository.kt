package pe.edu.upc.easymovie.features.movies.domain

interface MovieRepository {
    suspend fun getAllMovies(): List<Movie>
}