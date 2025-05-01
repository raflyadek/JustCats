package com.creospace.recipe_kmp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(FavoriteCats::class)], version = 2, exportSchema = false)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}