package com.creospace.recipe_kmp.data.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteCats(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @NonNull
    var id: String,

    @ColumnInfo(name = "url")
    var url: String? = ""
)
