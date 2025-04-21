package com.creospace.recipe_kmp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCats: FavoriteCats)

    @Delete
    suspend fun delete(favoriteCats: FavoriteCats)

    @Update
    suspend fun update(favoriteCats: FavoriteCats)

    @Query("SELECT * FROM favorite")
    suspend fun loadAll(): List<FavoriteCats>

}