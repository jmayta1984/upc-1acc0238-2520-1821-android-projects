package pe.edu.upc.easymovie.features.movies.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.edu.upc.easymovie.features.movies.data.local.dao.MovieDao
import pe.edu.upc.easymovie.features.movies.data.local.models.MovieEntity
import pe.edu.upc.easymovie.features.movies.data.remote.services.MovieService
import pe.edu.upc.easymovie.features.movies.domain.Movie
import pe.edu.upc.easymovie.features.movies.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl
@Inject constructor(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {
    override suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {

        try {
            val response = service.getAllMovies()
            if (response.isSuccessful) {
                response.body()?.let { moviesWrapperDto ->
                    return@withContext moviesWrapperDto.movies.map { movieDto ->
                        Movie(
                            id = movieDto.id,
                            title = movieDto.title,
                            overview = movieDto.overview,
                            posterPath = "https://image.tmdb.org/t/p/w500${movieDto.posterPath}",
                            isFavorite = dao.fetchById(movieDto.id).isNotEmpty()
                        )
                    }

                }
            }

        } catch (e: Exception) {
            return@withContext emptyList()
        }


        return@withContext emptyList()
    }

    override suspend fun insertMovie(movie: Movie) = withContext(Dispatchers.IO) {
        dao.insert(
            MovieEntity(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath,
                overview = movie.overview
            )
        )
    }

    override suspend fun deleteMovie(movie: Movie) = withContext(Dispatchers.IO) {
        dao.delete(
            MovieEntity(
                id = movie.id,
                title = movie.title,
                posterPath = movie.posterPath,
                overview = movie.overview
            )
        )
    }

    override suspend fun getAllFavorites(): List<Movie> = withContext(Dispatchers.IO) {
        return@withContext dao.fetchAll().map { movieEntity ->
            Movie(
                id = movieEntity.id,
                title = movieEntity.title,
                overview = movieEntity.overview,
                posterPath = movieEntity.posterPath,
                isFavorite = true
            )
        }
    }
}