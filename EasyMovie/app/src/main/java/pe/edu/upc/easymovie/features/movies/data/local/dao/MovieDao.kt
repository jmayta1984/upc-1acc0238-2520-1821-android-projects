package pe.edu.upc.easymovie.features.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.easymovie.features.movies.data.local.models.MovieEntity


@Dao
interface MovieDao {
    @Insert
    suspend fun insert(vararg entities: MovieEntity)

    @Delete
    suspend fun delete(vararg entities: MovieEntity)

    @Query("select * from movies")
    suspend fun fetchAll(): List<MovieEntity>

    @Query("select * from movies where id=:id")
    suspend fun fetchById(id: Int): List<MovieEntity>
}