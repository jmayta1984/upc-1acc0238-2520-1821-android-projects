package pe.edu.upc.easymovie.features.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.upc.easymovie.features.movies.domain.Movie
import pe.edu.upc.easymovie.features.movies.domain.MovieRepository
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun getAllMovies() {
        viewModelScope.launch {
            _movies.value = repository.getAllMovies()
        }

    }

    init {
        getAllMovies()
    }

    fun toggleFavorite(movie: Movie) {

        viewModelScope.launch {
            if (movie.isFavorite) {
                repository.deleteMovie(movie)
            } else {
                repository.insertMovie(movie)
            }

        }
        _movies.value = _movies.value.map { item ->
            if ((item.id) == movie.id) {
                movie.copy(isFavorite = !movie.isFavorite)
            } else {
                item
            }
        }


    }
}