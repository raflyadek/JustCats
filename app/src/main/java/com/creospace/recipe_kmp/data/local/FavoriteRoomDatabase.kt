package com.creospace.recipe_kmp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCats::class], version = 1, exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}