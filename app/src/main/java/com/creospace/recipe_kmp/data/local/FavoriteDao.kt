package com.creospace.recipe_kmp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCats: FavoriteCats)

    @Delete
    suspend fun delete(favoriteCats: FavoriteCats)

    @Update
    suspend fun update(favoriteCats: FavoriteCats)

    @Query("SELECT * FROM favorite")
    fun loadAll(): Flow<List<FavoriteCats>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun isFavorite(id: String): Flow<List<FavoriteCats>>

}