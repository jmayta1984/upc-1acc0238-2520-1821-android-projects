package pe.edu.upc.easymovie.features.movies.data.remote.services

import pe.edu.upc.easymovie.features.movies.data.remote.models.MoviesWrapperDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/{category}")
    suspend fun getAllMovies(
        @Path("category") category: String = "popular",
        @Query("api_key") apiKey: String = "3cae426b920b29ed2fb1c0749f258325"
    ): Response<MoviesWrapperDto>
}